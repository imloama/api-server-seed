package com.github.imloama.api.demo.service.impl;

import com.github.imloama.api.base.BaseServiceImpl;
import com.github.imloama.api.demo.mapper.UserMapper;
import com.github.imloama.api.demo.model.User;
import com.github.imloama.api.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements IUserService {
}
