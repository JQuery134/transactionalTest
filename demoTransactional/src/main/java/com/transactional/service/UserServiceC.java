package com.transactional.service;

import com.transactional.bean.User;
import com.transactional.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceC {
    @Autowired
    private UserDao userDao;

    @Transactional
    public int insert(User user) throws RuntimeException{
        int i = userDao.insert(user);
        try {
            int i1  = 1 / 0;
        } catch (Exception e) {
            new RuntimeException(e);
        }
        return i;
    }

}
