package com.github.imloama.api.demo.model;

import com.github.imloama.api.base.BaseModel;
import lombok.Data;

@Data
public class User extends BaseModel<User> {

    private String username;
    private String password;
}
