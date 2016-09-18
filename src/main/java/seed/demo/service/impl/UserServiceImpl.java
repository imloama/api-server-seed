package seed.demo.service.impl;

import org.springframework.stereotype.Service;

import seed.core.service.impl.BaseServiceImpl;
import seed.demo.mapper.UserMapper;
import seed.demo.models.User;
import seed.demo.service.IUserService;


/**
 * 注解采用dubbo的@service，为了采用dubbo提供的服务
 * 具体使用可以见本人的项目：https://github.com/mazhaoyong/dubbox-zookeeper-demo
 * 
 * @author mazhaoyong
 */
//@Service(protocol = { "dubbo" }, interfaceClass = IUserService.class)
//@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User>  implements IUserService{
	
	public UserMapper getMapper() {
		return (UserMapper)getBaseMapper();
	}

	@Override
	public User selectByLoginName(String name) {
		return this.getMapper().selectByLoginName(name);
	}
	
	
}
