package seed.demo.service;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

import seed.core.service.BaseService;
import seed.demo.mapper.UserMapper;
import seed.demo.models.User;


/**
 * 注解采用dubbo的@service，为了采用dubbo提供的服务
 * 具体使用可以见本人的项目：https://github.com/mazhaoyong/dubbox-zookeeper-demo
 * 
 * @author mazhaoyong
 */
@Service(protocol = { "dubbo" })
@Transactional
public class UserService extends BaseService<User>{
	
	public UserMapper getMapper() {
		return (UserMapper)getBaseMapper();
	}
	
	
}
