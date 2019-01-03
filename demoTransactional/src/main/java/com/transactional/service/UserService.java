package com.transactional.service;

import com.transactional.bean.User;
import com.transactional.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserServiceB userServiceB;
    @Autowired
    private UserServiceC userServiceC;

    /**
     * 注意事项一：设置rollbackFor属性，很多博客说默认只有RuntimeExcetion会触发回滚，
     * 经验证确实如此，所以rollbackFor最好应该设置如下：rollbackFor = {Exception.class}，
     * 当然具体业务具体处理，可能有的业务抛出的某些异常并不需要触发回滚，所以此时应该细化处理异常。
     * @param user
     * @return
     */
    @Transactional
    public int insert(User user){
        int i = userDao.insert(user);
        return i;
    }

    /**
     * 注意事项二：事务只有配置在public方法上，且是被外部调用时才有效
     * @param user
     * @return
     */
    @Transactional
    private int insert1(User user){
        int i = userDao.insert(user);
        return i;
    }

    /**
     * 注意事项三：外部调用时事物才有效。下面情况会回滚，如果去掉@Transactional注解，不会回滚
     * @param user
     * @return
     */
    @Transactional
    public int insert2(User user){
        int i =  this.insert(user);
        try {
            int a = 1/0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    /**
     * 事物嵌套:userServiceC.insert抛出异常，userDao.insert(user)和userServiceB.insert(user)会回滚。
     * 这里的所有事物传播属性都是PROPAGATION_REQUIRED
     * @param user
     * @return
     */
    @Transactional
    public int insert3(User user){
        int i = 0;
        int j = 0;
        int x = 0;
        try {
            x = userDao.insert(user);
            i = userServiceB.insert(user);
            j = userServiceC.insert(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return i+j;
    }

    /**
     * 事物嵌套:userServiceC.insertNew事物属性为REQUIRES_NEW，userServiceB.insertNew事物抛出异常后，userServiceC.insertNew事物不会回滚
     * @param user
     * @return
     */
    @Transactional
    public int insert4(User user){
        int i = 0;
        int j = 0;
        int x = 0;
        try {
            x = userDao.insert(user);
            j = userServiceC.insertNew(user);
            i = userServiceB.insertNew(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return i+j;
    }


}
