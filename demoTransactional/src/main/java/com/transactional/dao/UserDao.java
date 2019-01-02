package com.transactional.dao;

import com.transactional.bean.User;

public interface UserDao {

    /*int deleteByPrimaryKey(Integer userId);*/

    int insert(User user);

    /*int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser();*/
}
