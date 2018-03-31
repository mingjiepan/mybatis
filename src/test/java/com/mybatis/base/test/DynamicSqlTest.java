package com.mybatis.base.test;

import com.mybatis.base.domain.Employee;
import com.mybatis.base.mapper.DynamicSqlMapper;
import com.mybatis.base.mapper.EmployeeMapper;
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
public class DynamicSqlTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testIfAndWhere() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DynamicSqlMapper mapper = session.getMapper(DynamicSqlMapper.class);
            Employee employee = new Employee();
            employee.setId(1);

            List<Employee> employees = mapper.listEmployee(employee);
            System.out.println(employees);
//            ifAndWhere(mapper);//测试if和where


        } finally {
            session.close();
        }
    }
    @Test
    public void testTrim() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DynamicSqlMapper mapper = session.getMapper(DynamicSqlMapper.class);
            Employee employee = new Employee();
            employee.setId(1);

            List<Employee> employees = mapper.listEmployeeTrim(employee);
            System.out.println(employees);
        } finally {
            session.close();
        }
    }

    @Test
    public void testChoose() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DynamicSqlMapper mapper = session.getMapper(DynamicSqlMapper.class);
            Employee employee = new Employee();

            List<Employee> employees = mapper.listEmployeeChoose(employee);
            System.out.println(employees);
        } finally {
            session.close();
        }
    }

    @Test
    public void testForeach() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DynamicSqlMapper mapper = session.getMapper(DynamicSqlMapper.class);
            List<Employee> employees = mapper.selectByCollection(Arrays.asList(1, 2));
            System.out.println(employees);
        } finally {
            session.close();
        }
    }
}
