package seed.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import seed.demo.models.User;
import seed.demo.service.IUserService;

@RestController
public class UserController {

	//@Reference
	@Autowired
	private IUserService service;

	@RequestMapping(value = "/api/v1/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> index() throws Exception {
		return service.selectAll();
	}

	@RequestMapping(value = "/api/v1/users/page/{p}", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<User> page(@PathVariable("p") int p) throws Exception {
		return service.selectPage(p);
	}
	
	@RequestMapping(value = "/api/v1/users/byname/{name}", method = RequestMethod.GET)
	@ResponseBody
	public User selectUserByName(@PathVariable("name") String name) throws Exception {
		return service.selectByLoginName(name);
	}
	
	

}
