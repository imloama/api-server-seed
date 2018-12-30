package com.github.imloama.api.config.jwt;

import java.security.SecureRandom;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

/**
 * json web token 工具类 Created by mazhyb on 2016-01-25.
 */
public final class JWT {

	private JWT() {
	}

	/**
	 * api请求时
	 */
	public static final String API_TOKEN_KEY = "api-access-token";

	public static final byte[] SHARED_SECRET;

	static {
		SHARED_SECRET = new byte[32];
		SecureRandom random = new SecureRandom();
		random.nextBytes(SHARED_SECRET);
	}

	public static String newToken(JWTUser user) throws JOSEException {
		return newJWSObject(user).serialize();
	}

	public static JWSObject newJWSObject(JWTUser user) throws JOSEException {
		JWSSigner signer = new MACSigner(JWT.SHARED_SECRET);
		JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(user));
		jwsObject.sign(signer);
		return jwsObject;
	}

	public static boolean verify(JWSObject jwsObject) throws JOSEException {
		JWSVerifier verifier = new MACVerifier(JWT.SHARED_SECRET);
		return jwsObject.verify(verifier);
	}

	/**
	 * token有效期最大是默认24*3600秒（1天）
	 */
	public static final int EXP_TIMEOUT_SECOND = 86400;

	public static long newExp() {
		return newExp(EXP_TIMEOUT_SECOND);
	}

	/**
	 * 生成新的有效期
	 * 
	 * @param timeout
	 *            有效期多长时间，单位是秒
	 * @return
	 */
	public static long newExp(int timeout) {
		Calendar newexp = Calendar.getInstance();
		newexp.add(Calendar.SECOND, timeout);
		return newexp.getTimeInMillis();
	}

	public static JWTUser getJWTUser(String token) throws JWTException {
		if (StringUtils.isEmpty(token)) {
			throw new JWTException("没有找到token信息！");
		}
		try {
			JWSObject jwsObject = JWSObject.parse(token);
			if (JWT.verify(jwsObject)) {
				// 判断有效期，不在有效期内则直接抛出错误
				JWTUser user = new JWTUser(jwsObject.getPayload().toJSONObject());
				if (user.getExp() >= Calendar.getInstance().getTimeInMillis()) {
					return user;
				} else {
					throw new JWTException("token已经超过有效期！");
				}
			} else {
				throw new JWTException("token校验失败！");
			}
		} catch (Exception e) {
			throw new JWTException(e);
		}
	}

	public static JWTUser getJWTUser(HttpServletRequest request) throws JWTException {
		// 1. 从parameter中取
		String token = request.getParameter(API_TOKEN_KEY);
		if (StringUtils.isEmpty(token)) {
			// 2. 从header中取
			token = request.getHeader(API_TOKEN_KEY);
		}
		if (StringUtils.isEmpty(token)) {
			// 3. 从cookie中取
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (API_TOKEN_KEY.equals(cookie.getName())) {
					token = cookie.getValue();
				}
			}
		}
		return getJWTUser(token);
	}

}
