package com.lgx.springbootredis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgx.springbootredis.pojo.User;
import com.lgx.springbootredis.pojo.query.UserQuery;

import java.util.List;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/18 12:42
 */
public interface UserService {
    //查询所有用户
    public List<User> listUser();
    //根据用户名查询,并且分页展示
    public PageInfo<User> listUserByName(UserQuery userQuery);
    //根据用户id删除用户
    public Boolean deleteUserById(Integer id);
    //根据id查询用户
    public User queryUserById(Integer id);
    //修改用户
    public Boolean updateUser(User user);
    //新增用户
    public boolean addUser(User user);
    //用户登录
    public Boolean isUser(User user);
}
