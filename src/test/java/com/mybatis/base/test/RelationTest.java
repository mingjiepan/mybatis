package com.mybatis.base.test;

import com.mybatis.base.domain.Department;
import com.mybatis.base.domain.Employee;
import com.mybatis.base.mapper.DepartmentMapper;
import com.mybatis.base.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author panmingjie
 */
public class RelationTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testOne2One() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee employee = mapper.selectEmployee(1);
            System.out.println(employee);

        } finally {
            session.close();
        }
    }

    /**
     * 测试一对一的懒加载
     */
    @Test
    public void testOnStep() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee employee = mapper.selectById(1);
            System.out.println(employee.getAddress());
            System.out.println(employee.getDepartment());//只有实际访问的时候才会从数据库查询

        } finally {
            session.close();
        }
    }

    @Test
    public void testOne2Many() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);

            Department department = mapper.selectDepAndEmpById(1);
            System.out.println(department);

            System.out.println("-----------------------------");
            System.out.println(department.getEmployees());

        } finally {
            session.close();
        }
    }

    /**
     * 测试一对多的懒加载
     */
    @Test
    public void testOne2ManyStep() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);

            System.out.println("---------------");
            Department department = mapper.selectByStep(1);
            System.out.println(department.getDepartName());

            System.out.println(department.getEmployees());
        } finally {
            session.close();
        }
    }

}
