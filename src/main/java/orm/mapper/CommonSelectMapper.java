package orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.github.pagehelper.PageInfo;

import orm.provider.CommonSelectMapperProvider;


public interface CommonSelectMapper<T> {

	@SelectProvider(type = CommonSelectMapperProvider.class, method = "dynamicSQL")
	PageInfo<T> selectLikePage(int pageIndex,int pageSize, @Param("searchValue") String searchValue);

	@SelectProvider(type = CommonSelectMapperProvider.class, method = "dynamicSQL")
	PageInfo<T> selectPage(int pageIndex,int pageSize);

	@SelectProvider(type = CommonSelectMapperProvider.class, method = "dynamicSQL")
	List<T> selectByIdList(List<String> idList);

}
