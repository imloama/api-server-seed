package seed.demo.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisSessionController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@RequestMapping("/")
	@ResponseBody
	public String index(HttpServletRequest request,HttpServletResponse response){
		UUID uid = (UUID) request.getSession().getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		request.getSession().setAttribute("uid", uid);
		
		return String.format("sessionid:%s,uid:%s,request.class:%s,response.class:%s", 
				request.getSession().getId(),
				uid.toString(),
				request.getClass().getName(),
				response.getClass());
	}
	
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpSession session){
		String id = "spring:session:sessions:"+session.getId();
		boolean has = redisTemplate.hasKey(id);
		boolean hasuid = redisTemplate.hasKey("uid");
		
		redisTemplate.opsForValue().set("xxxx", "admin");
		
		String val = redisTemplate.opsForValue().get("xxxx");
		
		return String.format("session-id:%s, %s in redis;uid %s in redis,xxxx:%s", session.getId(),has,hasuid,val);
	}
	
}
