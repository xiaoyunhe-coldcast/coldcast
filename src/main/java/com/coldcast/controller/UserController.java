package com.coldcast.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coldcast.bean.User;
import com.coldcast.service.IUserService;
 
/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mht
 * @since 2018-06-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    private IUserService userSvc;
 
    @GetMapping(value = "/show")
    public JSONObject testEnum() {
        List<User> list = userSvc.findAll();
        JSONObject result = new JSONObject();
        result.put("users", list);
        return result;
    }
    
    @GetMapping(value = "/getByName")
    public JSONObject getByName(String name) {
        List<User> list = userSvc.getUserByName(name);
        JSONObject result = new JSONObject();
        result.put("users", list);
        return result;
    } 
}