package com.wei.Dao;

import com.wei.pojo.User;
import com.wei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoTest {

    static Logger logger=Logger.getLogger(String.valueOf(UserDaoTest.class));

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

    @Test
    public void testlog4j(){
        logger.info("info:进入了testlog4j");
        logger.debug("debug:进入了testlog4j");
        logger.error("error:进入了testlog4j");
    }

    @Test
    public void getUserBylimit(){
        SqlSession sqlSession=MybatisUtils.getSqlsession();
        UserDaoMapper mapper=sqlSession.getMapper(UserDaoMapper.class);

        HashMap<String,Integer> map=new HashMap<String, Integer>();
        map.put("startIndex",0);
        map.put("pageSize",2);

        List<User> UserLimit=mapper.getUserByLimit(map);
        for (User user:UserLimit)
        {
            System.out.println(user);
        }
        sqlSession.close();
    }

}
