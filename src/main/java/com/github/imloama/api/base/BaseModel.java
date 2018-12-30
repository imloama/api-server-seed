package com.github.imloama.api.base;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

public abstract class BaseModel<T extends Convert> extends Convert<T>{

    @TableId
    private Long id;
    /**
     * 时间戳
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ts;
    /**
     *
     */
    @TableLogic
    private Short dr;

    public Long getId() {
        return id;
    }

    public <T>T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public Date getTs() {
        return ts;
    }

    public <T>T setTs(Date ts) {
        this.ts = ts;
        return (T) this;
    }

    public Short getDr() {
        return dr;
    }

    public <T>T setDr(Short dr) {
        this.dr = dr;
        return (T) this;
    }
}
