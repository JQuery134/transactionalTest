package com.transactional.service;

import com.transactional.bean.User;
import com.transactional.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceB {
    @Autowired
    private UserDao userDao;

    @Transactional
    public int insert(User user) throws RuntimeException{
        int i = userDao.insert(user);
        return i;
    }

    @Transactional
    public int insertNew(User user) throws RuntimeException{
        int i = userDao.insert(user);
        try {
            int x = 1/0;
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return i;
    }

}
