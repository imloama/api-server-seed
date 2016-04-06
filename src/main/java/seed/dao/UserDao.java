package seed.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import seed.models.User;
import seed.orm.dao.BaseDao;
import seed.orm.mybatis.Page;

/**
 * 用户Dao接口
 * 
 * @author liuhm
 * @since 2015年12月16日
 **/
//@Repository
public interface UserDao extends BaseDao<User> {
    /**
     * 根据用户名查用户信息
     * @param code
     * @return
     */
	User selectByLoginName(@Param("code") String code);

    List<User> selectAllUsers(Page<User> page);
}