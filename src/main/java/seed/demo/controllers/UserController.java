package seed.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import seed.demo.models.User;
import seed.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/users")
	@ResponseBody
	public List<User> index() throws Exception{
		return service.selectAll();
	}
	
	@RequestMapping("/users/{p}")
	@ResponseBody
	public PageInfo<User> page(@PathVariable("p") int p) throws Exception{
		return service.selectPage(p);
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public User user(){
		return this.service.getMapper().selectByLoginName("admin");
	}
	
	
}
