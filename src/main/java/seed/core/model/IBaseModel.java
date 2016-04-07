package seed.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础模型，针对必须存在的字段统一命名
 * Created by liuhm on 2015/12/11.
 */
public interface IBaseModel extends Serializable {
    /**
     * 所有的表统一规范主键，使用id
     * @return
     */
    String getId();
    void setId(String id);

    /**
     * 时间戳，用于进行校验并发
     * @return
     */
    Date getTs();
    void setTs(Date ts);

    /**
     * 数据只做逻辑删除，不做物理删除。（赋值取值请使用DBConsts常量类）
     * 0：正常态
     * 1：删除态
     * @return
     */
    Short getDr();
    void setDr(Short dr);
}
