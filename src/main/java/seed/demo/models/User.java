package seed.demo.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import seed.core.model.BaseModel;

/**
 * 用户数据模型
 * 
 * @author liuhm
 * @since 2014年7月5日 下午12:07:20
 **/
@Entity
@Table(name="sm_user")
public class User extends BaseModel {

	private static final long serialVersionUID = 1657158559598921654L;

	private String code;

    private String name;

    private String orgId;

    private Short sex;

    private String idCard;

    private String phone;

    private String password;

    private String headerImg;

    private Short status;

    private String memo;


    public User() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}