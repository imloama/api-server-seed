package seed.core.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 模型的基础类
 *
 * Created by mazhyb on 2016-01-12.
 */
@MappedSuperclass
public abstract class BaseModel implements IBaseModel{

	private static final long serialVersionUID = 5043790384605350882L;

	/**
     * 主键
     */
    @Id
    private String id;

    /**
     * 时间戳
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date ts;
    /**
     *
     */
    private Short dr;


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Date getTs() {
        return this.ts;
    }

    @Override
    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public Short getDr() {
        return this.dr;
    }

    @Override
    public void setDr(Short dr) {
        this.dr = dr;
    }

}
