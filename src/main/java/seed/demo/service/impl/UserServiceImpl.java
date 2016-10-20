package seed.demo.service.impl;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import seed.core.service.impl.BaseServiceImpl;
import seed.demo.mapper.UserMapper;
import seed.demo.models.User;
import seed.demo.service.IUserService;


/**
 * 
 * @author mazhaoyong
 */
@MotanService
public class UserServiceImpl extends BaseServiceImpl<User>  implements IUserService{
	
	public UserMapper getMapper() {
		return (UserMapper)getBaseMapper();
	}

	@Override
	public User selectByLoginName(String name) {
		return this.getMapper().selectByLoginName(name);
	}
	
	
}
