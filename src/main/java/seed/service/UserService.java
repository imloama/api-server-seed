package seed.service;

import org.springframework.stereotype.Service;

import seed.dao.UserDao;
import seed.models.User;
import seed.pub.service.BaseService;

@Service
public class UserService extends BaseService<User> {

	
	public UserDao getDao(){
		return (UserDao)this.dao;
	}
	
	@Override
	public String getModelCode() {
		return "SM";
	}

}
