package seed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import seed.models.User;
import seed.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/users")
	@ResponseBody
	public List<User> index() throws Exception{
		return service.selectAll();
	}
	
}
