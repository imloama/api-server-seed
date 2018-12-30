package com.github.imloama.api.base;

import com.baomidou.mybatisplus.extension.activerecord.Model;


/**
 * <p>
 * 普通实体父类
 * </p>
 *
 * @author Caratacus
 */
public abstract class Convert<T extends Model> extends Model<T> {

    /**
     * 获取自动转换后的JavaBean对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T convert(Class<T> clazz) {
        return BeanConverter.convert(clazz, this);
    }
}