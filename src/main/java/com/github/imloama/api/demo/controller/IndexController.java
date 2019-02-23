package com.github.imloama.api.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.imloama.api.base.BaseController;
import com.github.imloama.api.demo.model.User;
import com.github.imloama.api.demo.service.IUserService;
import com.github.imloama.mybatisplus.bootext.base.APIResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class IndexController extends BaseController {

    @ApiOperation("hello")
    @RequestMapping("/hello")
    public APIResult index(){

        log.debug(" debug ");

        return APIResult.ok("ok");


    }

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "根据用户名查询用户")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public APIResult getUserByName(@RequestParam("username") String username){

        QueryWrapper<User> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = this.userService.getOne(queryWrapper);
        return APIResult.ok("ok", user);

    }

}
