package orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.UpdateProvider;

import orm.provider.BatchLogicDeleteMapperProvider;

public interface BatchLogicDeleteMapper<T> {
	@UpdateProvider(type = BatchLogicDeleteMapperProvider.class, method = "dynamicSQL")
	int batchDelete(List<T> arr);
}
