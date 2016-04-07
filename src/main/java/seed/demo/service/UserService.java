package seed.demo.service;

import org.springframework.stereotype.Service;

import seed.core.service.BaseService;
import seed.demo.mapper.UserMapper;
import seed.demo.models.User;

@Service
public class UserService extends BaseService<User>{
	
	public UserMapper getMapper() {
		return (UserMapper)getBaseMapper();
	}
	
	
}
