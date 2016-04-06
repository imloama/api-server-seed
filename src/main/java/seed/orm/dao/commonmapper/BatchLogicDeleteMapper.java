package seed.orm.dao.commonmapper;

import org.apache.ibatis.annotations.UpdateProvider;

import seed.orm.dao.provider.BatchLogicDeleteMapperProvider;

import java.util.List;

/**
 * Created by liuhaiming on 2015/12/28
 */
public interface BatchLogicDeleteMapper<T> {

    @UpdateProvider(type = BatchLogicDeleteMapperProvider.class, method = "dynamicSQL")
    int batchDelete(List<T> arr);

}
