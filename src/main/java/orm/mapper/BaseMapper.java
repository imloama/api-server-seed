package orm.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基础mapper
 * 
 * @author mazhaoyong@gmail.com
 *
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, CommonSelectMapper<T>, BatchLogicDeleteMapper<T> {
}
