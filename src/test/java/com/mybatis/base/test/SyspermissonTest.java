package com.mybatis.base.test;

import com.mybatis.base.domain.Syspermission;
import com.mybatis.base.mapper.SyspermissionMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author panmingjie
 */
public class SyspermissonTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testList() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            SyspermissionMapper sysuserMappper = session.getMapper(SyspermissionMapper.class);

            List<Syspermission> list = sysuserMappper.list("permission");

            list.forEach(System.out::println);

        } finally {
            session.close();
        }
    }

    @Test
    public void testMap() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            SyspermissionMapper sysuserMappper = session.getMapper(SyspermissionMapper.class);

            Map<String, Object> map = sysuserMappper.map(11);
            System.out.println(map);

        } finally {
            session.close();
        }
    }

    @Test
    public void testMapList() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            SyspermissionMapper sysuserMappper = session.getMapper(SyspermissionMapper.class);

            List<Map<String, Object>> permission = sysuserMappper.listMap("permission");
            permission.forEach(System.out::println);
        } finally {
            session.close();
        }
    }

    @Test
    public void testMapObj() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            SyspermissionMapper sysuserMappper = session.getMapper(SyspermissionMapper.class);

            Map<Integer, Syspermission> map = sysuserMappper.mapObj();
            System.out.println(map);
        } finally {
            session.close();
        }
    }
}
