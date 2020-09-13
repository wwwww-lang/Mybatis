package com.wei.Dao;

import com.wei.pojo.User;
import com.wei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void getUserLike(){
        SqlSession sqlSession =MybatisUtils.getSqlsession();
        UserDaoMapper userDao=sqlSession.getMapper(UserDaoMapper.class);
        List<User> userList = userDao.getUserLike("%J%");

        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void test(){
         SqlSession sqlSession =MybatisUtils.getSqlsession();
         UserDaoMapper userDao=sqlSession.getMapper(UserDaoMapper.class);
         List<User> userList = userDao.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void userTest(){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDaoMapper mapper = sqlsession.getMapper(UserDaoMapper.class);
        User userID = mapper.getUserID(1);
        System.out.println(userID);

        sqlsession.close();
    }
    @Test
    public void addUser(){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDaoMapper mapper = sqlsession.getMapper(UserDaoMapper.class);

        mapper.addUser(new User(4,"Liln","12343122"));

        sqlsession.commit();
        sqlsession.close();
    }

    @Test
    public void UpdateUser(){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDaoMapper mapper = sqlsession.getMapper(UserDaoMapper.class);

        mapper.UpdateUser(new User(4,"Lilwayn","1234312"));

        sqlsession.commit();
        sqlsession.close();
    }


    @Test
    public void deleteUser(){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDaoMapper mapper = sqlsession.getMapper(UserDaoMapper.class);

        mapper.deleteUser(4);

        sqlsession.commit();
        sqlsession.close();
    }

}
