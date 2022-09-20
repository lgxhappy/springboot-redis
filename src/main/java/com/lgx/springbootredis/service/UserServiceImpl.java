package com.lgx.springbootredis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgx.springbootredis.dao.UserDao;
import com.lgx.springbootredis.pojo.User;
import com.lgx.springbootredis.pojo.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/18 12:43
 */
@Service  //交给spring容器管理
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listUser() {
        List<User> users = userDao.listUser();
        return users;
    }

    @Override
    public PageInfo<User> listUserByName(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        return new PageInfo<User>(userDao.listUserByName(userQuery));
    }

    @Override
    public Boolean deleteUserById(Integer id) {
        int i = userDao.deleteUserById(id);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User queryUserById(Integer id) {
        User user = userDao.queryUserById(id);
        return user;
    }
    @Override
    public Boolean updateUser(User user) {
        int i = userDao.updateUser(user);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user) >0 ? true :false;
    }

    @Override
    public Boolean isUser(User user) {
        int num = userDao.isUser(user);
        if(num>0){
            return true;
        }else{
           return false;
        }
    }


}
