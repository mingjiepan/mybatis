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

/**
 * @author panmingjie
 *
 *
 * 1、接口编程  Dao => DaoImpl
 *   mybatis Mapper => Mapper.xml
 * 2、SqlSession表示与数据库的会话，用完需关闭，与connect一样为非线程安全的，因此不能将SqlSession设置为成员变量，因为
 * 若是这样，当有多个线程使用同一个SqlSession时，其中一个线程关闭session时，会导致其他的线程也不能使用
 * 3、mapper没有实现类，mybatis会生成一个代理对象。
 *
 */
public class BaseTest {

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 旧版本的使用方法
     * 1、根据xml配置文件，创建一个SqlSessionFactory对象
     * 2、sql映射文件，配置每一个sql，及封装规则
     * 3、将sql映射文件注册到全局配置文件
     */
    @Test
    public void testFirst() {
        //SqlSession表示与数据库的会话
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //参数1，sql的唯一标识，一般为配置文件的namespace+id，第二个为sql的入参
            Sysuser sysuser = (Sysuser) session.selectOne("com.mjie.mybatis.SysuserMapper.selectUserByid", 1);

            //这种方式有很大的缺点，第一个，sql的唯一标识较长。第二个，参数可以随便传入，编译期间无法知道

            System.out.println(sysuser.toString());
        } finally {
            session.close();
        }
    }

    /**
     * 获取接口的实现类（代理类）
     * 将接口和映射文件进行绑定
     */
    @Test
    public void testSecond() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            SysuserMappper sysuserMappper = session.getMapper(SysuserMappper.class);

            System.out.println(sysuserMappper);

            Sysuser sysuser = sysuserMappper.selectUserById(1);
            System.out.println(sysuser);
        } finally {
            session.close();
        }
    }
}
