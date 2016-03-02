package seed.orm.dao.commonmapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import seed.orm.dao.provider.CommonSelectMapperProvider;
import seed.orm.mybatis.Page;

import java.util.List;

/**
 * Created by liuhaiming on 2016/01/08
 */
public interface CommonSelectMapper<T> {
    @SelectProvider(type = CommonSelectMapperProvider.class, method = "dynamicSQL")
    List<T> selectLikePage(Page<T> page, @Param("searchValue") String searchValue);

    @SelectProvider(type = CommonSelectMapperProvider.class, method = "dynamicSQL")
    List<T> selectPage(Page<T> page);

    @SelectProvider(type = CommonSelectMapperProvider.class, method = "dynamicSQL")
    List<T> selectByIdList(List<String> idList);

}

