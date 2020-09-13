package com.wei.Dao;

import com.wei.pojo.User;

import java.util.List;

public interface UserDaoMapper {

    //查询全部用户
    List<User> getUserList();

    //模糊查询
    List<User> getUserLike(String value);

    //根据ID查询用户
    User getUserID(int Id);

    //插入一个用户
    int addUser(User user);

    //修改用户
    int UpdateUser(User user);

    //删除用户
    int deleteUser(int Id);
}
