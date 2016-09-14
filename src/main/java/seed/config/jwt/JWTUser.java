package seed.config.jwt;

import net.minidev.json.JSONObject;

/**
 * 将对象用于保存到jwt的playground中，保证用户类型、用户主键等主要信息
 *
 * Created by mazhyb on 2016-01-26.
 */
public class JWTUser extends JSONObject {

    private static final long serialVersionUID = 4421098083769043639L;
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EXP = "exp";//有效期截止

    public JWTUser(JSONObject object){
        this.putAll(object);
    }

    public JWTUser(String id,String name){
        this(id,name,JWT.newExp());
    }

    /**
     * 构造函数
     * @param type 用户类型：见APIConsts，
     * @param id 用户ID
     * @param name 用户名称
     * @param exp 有效期
     */
    public JWTUser(String id,String name,long exp){
        this.put(KEY_ID,id);
        this.put(KEY_NAME,name);
        this.put(KEY_EXP, exp);
    }


    public String getId(){
        return (String)this.get(KEY_ID);
    }

    public String getName(){
        return (String)this.get(KEY_NAME);
    }

    public long getExp(){
        return (Long)this.get(KEY_EXP);
    }



}
