package com.transactional.controller;

import com.transactional.bean.User;
import com.transactional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    public int insert(){
        User user = new User();
        user.setUserName("包华杰");
        user.setPassword("123");
        user.setPhone("234124");
        return userService.insert4(user);
    }
}
