package seed.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

import seed.config.jwt.JWT;
import seed.config.jwt.JWTUser;

@RestController
public class JWTController {

	@RequestMapping(value = "/api/v1/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("username") String username,
			@RequestParam("secretkey") String secretkey){
		//测试用，直接判断
		if("admin".equals(username) && "123".equals(secretkey)){
			JWTUser user = new JWTUser("1",username);
			try {
				return JWT.newToken(user);
			} catch (JOSEException e) {
				throw new RuntimeException(e.getMessage(),e);
			}
		}
		throw new RuntimeException("验证用户失败！");
	}
	
	
	
	
}
