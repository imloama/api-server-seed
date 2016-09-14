package seed.core.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import seed.core.exception.BusinessException;
import seed.core.model.IBaseModel;

public interface IBaseService<T  extends IBaseModel>{

	public List<T> selectAll() throws Exception;
	
	  public PageInfo<T> selectPage(int p) ;

	
	public int save(T entity) throws BusinessException ;

	public int save(List<T> arr) throws BusinessException ;
	
	public int delete(T entity) throws BusinessException ;
	
	public int delete(List<T> entitys) throws BusinessException ;
	
	public int update(T entity) throws BusinessException;

	public int update(List<T> entitys) throws BusinessException ;


	public List<T> selectByCondition(T entity) throws BusinessException;


	
}
