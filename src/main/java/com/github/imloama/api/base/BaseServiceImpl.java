package com.github.imloama.api.base;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * <p>
 * 基础Service实现 继承于Mybatis-plus
 * </p>
 *
 * @author Caratacus
 */
//@Transactional(readOnly = true)
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> implements BaseService<T> {


    @Autowired
    protected M baseMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private Snowflake snowflake;

    /**
     * <p>
     * 判断数据库操作是否成功
     * </p>
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentModelClass() {
        return ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * <p>
     * 批量操作 SqlSession
     * </p>
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     */
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(currentModelClass()));
    }

    /**
     * 获取SqlStatement
     *
     * @param sqlMethod
     * @return
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    public Cache getCache() {
        return this.cacheManager.getCache(Consts.CACHE_NAME);
    }

    @Override
    public Cache getCache(String name) {
        return this.cacheManager.getCache(name);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(T entity) {
        entity.setId(this.snowflake.nextId());
        entity.setDr(Consts.DR_DEFAULT);
        entity.setTs(new Date());
        boolean result = retBool(baseMapper.insert(entity));
        if(result){
            this.getCache().put(entity.getId(), entity);
        }
        return result;
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        //批量对象插入 不存在直接返回true
        if (CollectionUtils.isEmpty(entityList)) {
            return true;
        }
        for(T t: entityList){
            t.setId(this.snowflake.nextId());
        }
        int i = 0;
        Map<Long,T> values = Maps.newHashMap();
        String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (T anEntityList : entityList) {
                batchSqlSession.insert(sqlStatement, anEntityList);
                values.put(anEntityList.getId(), anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        Cache cache = this.getCache();
        for(Map.Entry<Long,T> entry:values.entrySet()){
            cache.put(entry.getKey(), entry.getValue());
        }
        return true;
    }

    /**
     * <p>
     * TableId 注解存在更新记录，否插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdate(T entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal)) {
                    return save(entity);
                } else {
                    /*
                     * 更新成功直接返回，失败执行插入逻辑
                     */
                    return Objects.nonNull(getById((Serializable) idVal)) ? updateSelectiveById(entity) : save(entity);
                }
            } else {
                throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        //批量对象插入 不存在直接返回true
        if (CollectionUtils.isEmpty(entityList)) {
            return true;
        }
        Class<?> cls = currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        int i = 0;
        Map<Long,T> map = Maps.newHashMap();
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (T entity : entityList) {
                if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                    Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                    if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) {
                        entity.setId(this.snowflake.nextId());
                        map.put(entity.getId(), entity);
                        batchSqlSession.insert(sqlStatement(SqlMethod.INSERT_ONE), entity);
                    } else {
                        map.put(entity.getId(), entity);
                        MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                        param.put(Constants.ENTITY, entity);
                        batchSqlSession.update(sqlStatement(SqlMethod.UPDATE_BY_ID), param);
                    }
                    //不知道以后会不会有人说更新失败了还要执行插入 😂😂😂
                    if (i >= 1 && i % batchSize == 0) {
                        batchSqlSession.flushStatements();
                    }
                    i++;
                } else {
                    throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
                }
            }
            batchSqlSession.flushStatements();
        }
        if(map.size()>0){
            Cache cache = this.getCache();
            for(Map.Entry<Long,T> entry:map.entrySet()){
                cache.put(entry.getKey(), entry.getValue());
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        boolean result = SqlHelper.delBool(baseMapper.deleteById(id));
        if(result){
            this.getCache().evict(id);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Wrapper<T> queryWrapper) {
        this.getCache().clear();//清理所有缓存
        return SqlHelper.delBool(baseMapper.delete(queryWrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSelectiveById(T entity) {
        boolean result = retBool(baseMapper.updateById(entity));
        if(result){
            this.getCache().put(entity.getId(), entity);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        boolean result = retBool(baseMapper.updateById(entity));
        if(result){
            this.getCache().put(entity.getId(), entity);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        boolean result = retBool(baseMapper.update(entity, updateWrapper));
        if(result){
            this.getCache().clear();
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        //批量对象插入 不存在直接返回true
        if (CollectionUtils.isEmpty(entityList)) {
            return true;
        }
        int i = 0;
        String sqlStatement = sqlStatement(SqlMethod.UPDATE_BY_ID);
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (T anEntityList : entityList) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, anEntityList);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        for(T t:entityList){
            this.getCache().put(t.getId(), t);
        }
        return true;
    }

    @Cacheable(value = Consts.CACHE_NAME, key = "#id")
    @Transactional(readOnly = true)
    @Override
    public T getById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public int count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(baseMapper.selectCount(queryWrapper));
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    @Transactional(readOnly = true)
    @Override
    public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Transactional(readOnly = true)
    @Override
    public <R> List<R> listObjs(Wrapper<T> queryWrapper, Function<? super Object, R> mapper) {
        return baseMapper.selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public <R> IPage<R> pageEntities(IPage page, Wrapper<T> wrapper, Function<? super T, R> mapper) {
        return page(page, wrapper).convert(mapper);
    }

    @Transactional(readOnly = true)
    @Override
    public <R> List<R> entitys(Wrapper<T> wrapper, Function<? super T, R> mapper) {
        return list(wrapper).stream().map(mapper).collect(Collectors.toList());
    }


    private <K> Map<K, T> list2Map(List<T> list, SFunction<T, K> column) {
        if (list == null) {
            return Collections.emptyMap();
        }
        Map<K, T> map = new LinkedHashMap<>(list.size());
        for (T t : list) {
            Field field = ReflectionUtils.findField(t.getClass(), getColumn(LambdaUtils.resolve(column)));
            if (Objects.isNull(field)) {
                continue;
            }
            ReflectionUtils.makeAccessible(field);
            Object fieldValue = ReflectionUtils.getField(field, t);
            map.put((K) fieldValue, t);
        }
        return map;
    }

    @Transactional(readOnly = true)
    @Override
    public <K> Map<K, T> list2Map(Wrapper<T> wrapper, SFunction<T, K> column) {
        return list2Map(list(wrapper), column);
    }

    private String getColumn(SerializedLambda lambda) {
        return StringUtils.resolveFieldName(lambda.getImplMethodName());
    }
}