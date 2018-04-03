package com.mybatis.base.test;

import com.mybatis.base.domain.Pattern;
import com.mybatis.base.domain.Sysuser;
import com.mybatis.base.mapper.PatternMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author panmingjie
 */
public class MyTypeHandlerTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testselect() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PatternMapper mapper = session.getMapper(PatternMapper.class);
            Pattern pattern = mapper.selectByPrimary(1);
            System.out.println(pattern);
        } finally {
            session.close();
        }
    }

    @Test
    public void testInsertByHandler() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PatternMapper mapper = session.getMapper(PatternMapper.class);
            Pattern pattern = new Pattern();
            List<String> indexs = Arrays.asList("mj", "KD", "KB");
            pattern.setIndexs(indexs);
            pattern.setAddress("beijing");
            mapper.insertByHandler(pattern);

            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testInsertByNoHandler() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PatternMapper mapper = session.getMapper(PatternMapper.class);
            Pattern pattern = new Pattern();
            List<String> indexs = Arrays.asList("WD", "HH", "HH");
            pattern.setIndexs(indexs);
            pattern.setAddress("shanghai");
            mapper.insertByNoHandler(pattern);
            session.commit();
        } finally {
            session.close();
        }
    }
}
