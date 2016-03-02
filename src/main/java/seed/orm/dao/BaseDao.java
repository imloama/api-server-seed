package seed.orm.dao;

import seed.orm.dao.commonmapper.BatchLogicDeleteMapper;
import seed.orm.dao.commonmapper.CommonSelectMapper;
import seed.orm.dao.commonmapper.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liuhm on 2015/12/12
 */
public interface BaseDao<T> extends Mapper<T>, InsertListMapper<T>, BatchLogicDeleteMapper<T>, CommonSelectMapper<T> {
}
