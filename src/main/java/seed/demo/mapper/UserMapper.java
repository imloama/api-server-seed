package seed.demo.mapper;

import org.apache.ibatis.annotations.Param;

import orm.mapper.BaseMapper;
import seed.demo.models.User;

public interface UserMapper extends BaseMapper<User> {
	/**
	 * 根据用户名查用户信息
	 * 
	 * @param code
	 * @return
	 */
	User selectByLoginName(@Param("code") String code);

}