package com.Music.Model;

import com.Music.Model.DataIntance.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengzhi on 2016/8/31.
 */
public class UserTableModel {
    private String resource = "config/mybatis/mybatisConfig.xml";
    private InputStream inputStream=null;
    private SqlSessionFactory sqlSessionFactory=null;
    private SqlSession session=null;

    public void create() {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    public List findUser(String username) {
        String Class = "com.Music.Model.UserTableMapper.findUser";
        List<User> list = session.selectList(Class,username);
        session.close();
        return list;
    }

    public List getUser() {
        String Class = "com.Music.Model.UserTableMapper.getUser";
        List<User> list = session.selectList(Class);
        session.close();
        return list;
    }

    public User getUser(int id) {
        String Class = "com.Music.Model.UserTableMapper.getUserById";
        User user = session.selectOne(Class,id);
        session.close();
        return user;
    }

    public boolean addUser(User user) {
        String Class = "com.Music.Model.UserTableMapper.addUser";
        session.insert(Class, user);
        session.commit();
        session.close();
        return true;
    }

    public boolean lockUser(User user) {
        String Class = "com.Music.Model.UserTableMapper.lockUser";
        session.update(Class,user);
        session.commit();
        session.close();
        return true;
    }
}
