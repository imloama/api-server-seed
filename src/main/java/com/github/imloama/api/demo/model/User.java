package com.github.imloama.api.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.imloama.mybatisplus.bootext.base.BaseModel;
import lombok.Data;

@Data
@TableName(User.TABLE_NAME)
public class User extends BaseModel<User,Long> {

    public static final String TABLE_NAME = "user";

    @TableId
    private Long id;

    private String username;
    private String password;

    @Override
    public Long getPrimaryKey() {
        return this.id;
    }
}
