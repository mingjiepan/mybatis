package com.mybatis.base.test;

import com.mybatis.base.domain.Sysuser;
import com.mybatis.base.mapper.SysuserMappper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author panmingjie
 */
public class CRUDTest {

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * mybatis允许增删改的返回值可定义为int、long、boolean、void
     * 当操作的数目超过1条时，返回true
     */
    @Test
    public void testInsert() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Sysuser sysuser = new Sysuser();
            sysuser.setUsercode("hello123");
            sysuser.setUsername("hello21312");
            sysuser.setPassword("123456he21llo");

            SysuserMappper mapper = session.getMapper(SysuserMappper.class);
            int insert = mapper.insertUser(sysuser);
            System.out.println(sysuser.getId());

            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testMultiParam() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            SysuserMappper mapper = session.getMapper(SysuserMappper.class);
            Sysuser sysuser = mapper.findUserByIdAndUsername(4, "hello");
            System.out.println(sysuser);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testParamMap() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            SysuserMappper mapper = session.getMapper(SysuserMappper.class);

            Map<String, String> map = new HashMap<>();
            map.put("usercode", "hello");
            map.put("username", "hello");
            Sysuser sysuser = mapper.findUserByUsercodeAndUsername(map);
            System.out.println(sysuser);
            session.commit();
        } finally {
            session.close();
        }
    }

}
