package com.github.imloama.api.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.imloama.api.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
