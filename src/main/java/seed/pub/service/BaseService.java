package seed.pub.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import seed.orm.consts.DBConsts;
import seed.orm.dao.BaseDao;
import seed.pub.exception.BusinessException;
import seed.pub.model.IBaseModel;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by liuhaiming on 15/8/12
 */
public abstract class BaseService<T extends IBaseModel> {

    @Autowired
    protected BaseDao<T> dao;

    public T get(String id) {
        return dao.selectByPrimaryKey(id);
    }

    public List<T> get(List<String> ids) {
        return dao.selectByIdList(ids);
    }

    public List<T> selectAll() throws Exception{
        T t = getGenericType().newInstance();

        t.setDr(DBConsts.DR_NORMAL);

        return dao.select(t);
    }

    /*
    public Page<T> selectPage(AbstractDataTableQueryArgWapper wapper) {
        Page<T> page = new Page<>(wapper.getPageNum(), wapper.getLength());
        page.setOrderStr(wapper.getOrderStr());

        if ( !StringUtils.isEmpty( StringUtils.trim( wapper.getSearchText() ) ) ) {
            dao.selectLikePage(page, wapper.getSearchText());
        } else {
            dao.selectPage(page);
        }

        return page;
    }
    */

    /**
     * 模块编码
     * @return
     */
    public abstract String getModelCode();

	/**
     * 获取泛型类型
     * @return 泛型类型
     */
    private Class<T> getGenericType() throws BusinessException{
        // 通过dao找到泛型类的类型
        Class<T> cls = getGenericType(dao.getClass());
        if(cls == null) {
            throw new BusinessException("无法获取Mapper<T>泛型类型:" + dao.getClass().getName());
        }

        return cls;
    }

    private Class<T> getGenericType(Class cls) {
        Type[] types = cls.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType t = (ParameterizedType) type;
                return (Class<T>) t.getActualTypeArguments()[0];
            } else if(type instanceof Class) {
                return getGenericType((Class<T>)type);
            }
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(T entity) throws BusinessException{
        int result;
        try {
           // entity.setId(IdGenerator.getInstance().nextId(getModelCode()));
            entity.setDr(DBConsts.DR_NORMAL);
            setNewTs(entity);
            result = dao.insert(entity);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<T> arr) throws BusinessException {

        Date ts = createTs();

        for (T t : arr) {
          //  t.setId(IdGenerator.getInstance().nextId(getModelCode()));
            t.setDr(DBConsts.DR_NORMAL);
            t.setTs(ts);
        }

        int result;

        try {
            result = dao.insertList(arr);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

        return result;
    }

    public Date createTs() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ts;
        try {
            ts = format.parse(format.format(new Date()));
        } catch (ParseException e) {
            ts = new Date();
        }

        return ts;
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(T entity) throws BusinessException{
        int result;
        T newEntity;

        try {
            validateTs(entity);
            newEntity = (T)entity.getClass().newInstance();
            newEntity.setId(entity.getId());
            newEntity.setDr(DBConsts.DR_DELETE);
            setNewTs(newEntity);

            result = dao.updateByPrimaryKeySelective(newEntity);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(List<T> entitys) throws BusinessException{
        int result = dao.batchDelete(entitys);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(T entity) throws BusinessException{
        int result;

        try {
            validateTs(entity);
            setNewTs(entity);
            result = dao.updateByPrimaryKey(entity);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(List<T> entitys) throws BusinessException{
        int result = 0;
        for (T entity : entitys) {
            result += update(entity);
        }
        return result;
    }

    private void validateTs(T entity) throws BusinessException {
        if(entity == null || entity.getTs() == null) {
            return;
        }

        T origEntity = dao.selectByPrimaryKey(entity.getId());

        if(entity.getTs().compareTo(origEntity.getTs()) != 0) {
            throw new BusinessException("当前数据已被他人修改，请刷新！");
        }
    }

    protected void setNewTs(T entity) throws BusinessException {
        //由于mysql不支持毫秒级,所以设置TS到秒
        entity.setTs(createTs());
    }

    @SuppressWarnings("unchecked")
    public int updateStatus(T entity, short status) throws BusinessException{

        int result;

        T newEntity;

        try {
            validateTs(entity);
            newEntity = (T)entity.getClass().newInstance();
            newEntity.setId(entity.getId());
            PropertyUtils.setSimpleProperty(newEntity, DBConsts.FIELD_STATUS, status);
            setNewTs(newEntity);
            result = dao.updateByPrimaryKeySelective(newEntity);

        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

        return result;
    }


    public int enable(T entity) throws BusinessException{
        return updateStatus(entity, DBConsts.STATUS_ENABLE);
    }

    public int enable(List<T> entitys) throws BusinessException{
        int result = 0;
        for (T entity : entitys) {
            result += enable(entity);
        }
        return result;
    }

    public int disable(T entity) throws BusinessException{
        return updateStatus(entity, DBConsts.STATUS_DISABLE);
    }

    public int disable(List<T> entitys) throws BusinessException {
        int result = 0;
        for (T entity : entitys) {
            result += disable(entity);
        }
        return result;
    }

    public List<T> selectByCondition(T entity) throws BusinessException {
        Field[] fields = entity.getClass().getDeclaredFields();

        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        for (Field field : fields) {
            String val;
            try {
                val = BeanUtils.getProperty(entity, field.getName());
            } catch (IllegalAccessException e) {
                throw new BusinessException(e);
            } catch (InvocationTargetException e) {
                throw new BusinessException(e);
            } catch (NoSuchMethodException e) {
                throw new BusinessException(e);
            }

            if ( !StringUtils.isEmpty( val ) ) {
                criteria.andEqualTo(field.getName(), val);
            }
        }

        return dao.selectByExample(example);

    }

}
