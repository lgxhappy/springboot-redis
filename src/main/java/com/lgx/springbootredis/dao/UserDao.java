package com.lgx.springbootredis.dao;

import com.lgx.springbootredis.pojo.User;
import com.lgx.springbootredis.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/18 12:29
 */
@Mapper  //告诉spring boot这是一个mapper接口
@Repository  //将UserDao交给spring容器管理
public interface UserDao {
    //查询所有用户
    public List<User> listUser();
    //根据用户名查询,并且分页展示
    public List<User> listUserByName(UserQuery userQuery);
    //根据用户id删除用户
    public int deleteUserById(Integer id);
    //根据id查询用户
    public User queryUserById(Integer id);
    //修改用户信息
    public int updateUser(User user);
    //新增用户
    public int addUser(User user);
    //用户登录
    public int isUser(User user);

}
