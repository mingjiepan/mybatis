package com.mybatis.base.test;

import com.mybatis.base.domain.Employee;
import com.mybatis.base.domain.Syspermission;
import com.mybatis.base.domain.Sysuser;
import com.mybatis.base.mapper.EmployeeMapper;
import com.mybatis.base.mapper.SyspermissionMapper;
import com.mybatis.base.mapper.SysuserMappper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author panmingjie
 */
public class CacheTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 两级缓存
     * 一级缓存(本地缓存，SqlSession级别的)：
     *      1. 与数据库同一次会话期间查询到的数据会放在缓存中，以后，若获取相同的数据直接从缓存拿，而不是再去数据库查询
     *      2. 一级缓存是一直存在的，一个SqlSession对应一个一级缓存
     * 一级缓存失效的情况：
     *      1. 不同的SqlSession
     *      2. 相同的SqlSession，不同的查询条件
     *      3. 相同的SqlSession，但是中间有涉及到增删改操作
     *      4. 手动清除缓存
     * 二级缓存：基于namespace的二级缓存
     *      1. 所有查询的数据都会默认先存在一级缓存中，只有会话提交或者关闭后，一级缓存中的数据才会转移到二级缓存中
     *      2. 二级缓存开启步骤
     *          1. 全局配置setting cacheEnabled = true
     *          2. 在mapper配置文件，添加cache标签
     *          3. pojo实现Serializable接口
     *
     *缓存设置：
     *      1. 全局配置cacheEnabled 是对于二级缓存而言的，一级缓存是一直存在的
     *      2. 在配置文件select标签的useCache属性也是对于二级缓存而言的，一级缓存是一直存在的
     *      3. 配置文件的增删改的flushCache标签，若设置为true，则每次增删改后便会清空一级和二级缓存；select标签的flushCache设置为true，
     *      则每次查询都会从数据库查找，也就是不开启一级和二级缓存。
     *      4. SqlSession.clearCache()，由于是用sqlsession对象调用的，也就是针对SqlSession而言的。因此这个不会影响二级缓存。
     *      5. localCacheScope：本地缓存作用域，一级缓存session
     *
     *数据从缓存获取数据，都是从二级缓存获取，再从一级缓存获取
     */
    @Test
    public void testFirstCache() {
        SqlSession session1 = sqlSessionFactory.openSession();
        try {
            SysuserMappper mapper = session1.getMapper(SysuserMappper.class);
            Sysuser sysuser = mapper.selectUserById(1);
            System.out.println(sysuser);
            System.out.println("-------------------------------------------------------");
            Sysuser sysuser1 = mapper.selectUserById(1);
            System.out.println(sysuser1);
            System.out.println(sysuser == sysuser1);

        } finally {
            session1.close();
        }
    }

    @Test
    public void testFirstCache2() {
        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try {
            SysuserMappper mapper = session1.getMapper(SysuserMappper.class);
            Sysuser sysuser = mapper.selectUserById(1);
            System.out.println(sysuser);
            session1.close();
            System.out.println("-------------------------------------------------------");

            SysuserMappper mapper1 = session2.getMapper(SysuserMappper.class);
            Sysuser sysuser1 = mapper1.selectUserById(1);
            System.out.println(sysuser1);
            System.out.println(sysuser == sysuser1);
        } finally {
            session2.close();
        }
    }

    @Test
    public void testFirstCache3() {
        SqlSession session1 = sqlSessionFactory.openSession();
        try {
            SysuserMappper mapper = session1.getMapper(SysuserMappper.class);
            Sysuser sysuser = mapper.selectUserById(1);
            System.out.println(sysuser);
            System.out.println("-------------------------------------------------------");
            session1.clearCache();
            Sysuser sysuser1 = mapper.selectUserById(1);
            System.out.println(sysuser1);
            System.out.println(sysuser == sysuser1);

        } finally {
            session1.close();
        }
    }

    @Test
    public void testFirstCache4() {
        SqlSession session1 = sqlSessionFactory.openSession();
        try {
            SysuserMappper mapper = session1.getMapper(SysuserMappper.class);
            Sysuser sysuser = mapper.selectUserById(1);
            System.out.println(sysuser);
            System.out.println("-------------------------------------------------------");


            Sysuser newUser = new Sysuser();
            newUser.setPassword("123");
            newUser.setUsername("mmmmmm");
            newUser.setUsercode("mmcc11");
            mapper.insertUser(newUser);

            System.out.println("------------------------");

            Sysuser sysuser1 = mapper.selectUserById(1);
            System.out.println(sysuser1);
            System.out.println(sysuser == sysuser1);
        } finally {
            session1.close();
        }
    }

    @Test
    public void testFirstCache5() {
        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try {
            SyspermissionMapper mapper = session1.getMapper(SyspermissionMapper.class);
            List<Syspermission> permission = mapper.list("permission");
            System.out.println(permission);
            session1.close();
            System.out.println("-------------------------------------------------------");

            SyspermissionMapper mapper1 = session2.getMapper(SyspermissionMapper.class);
            List<Syspermission> permission2 = mapper1.list("permission");
            System.out.println(permission2);
        } finally {
            session2.close();
        }
    }
}
