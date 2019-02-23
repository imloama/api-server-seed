package com.github.imloama.api.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.imloama.mybatisplus.bootext.base.BaseModel;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@TableName(User.TABLE_NAME)
public class User extends BaseModel<User,Long> implements UserDetails {


    public static final String TABLE_NAME = "user";

    @TableId
    private Long id;

    private String username;
    private String password;

    @Override
    public Long getPrimaryKey() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO 调整为实际的权限
        return Lists.newArrayList(new SimpleGrantedAuthority("admin"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
