package seed.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orm.mapper.BaseMapper;
import seed.core.service.BaseService;
import seed.demo.mapper.UserMapper;
import seed.demo.models.User;

@Service
public class UserService extends BaseService<User>{

	@Autowired
	public UserMapper mapper;
	
	public UserMapper getMapper() {
		return mapper;
	}
	
	public void setMapper(UserMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BaseMapper<User> getBaseMapper() {
		return getMapper();
	}
	
	
}
