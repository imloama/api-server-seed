package seed.pub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 模型的基础类
 *
 * Created by mazhyb on 2016-01-12.
 */
@MappedSuperclass
public abstract class BaseModel implements IBaseModel{

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
