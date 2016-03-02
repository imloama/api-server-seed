package seed.orm.dao.commonmapper;

import org.apache.ibatis.annotations.InsertProvider;

import seed.orm.dao.provider.InsertListMapperProvider;

import java.util.List;

/**
 * Created by liuhaiming on 2016/1/29.
 */
public interface InsertListMapper<T> {

    /**
     * 批量插入，支持数据库自增字段，支持回写
     *
     * @param recordList
     * @return
     */
    @InsertProvider(type = InsertListMapperProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);
}