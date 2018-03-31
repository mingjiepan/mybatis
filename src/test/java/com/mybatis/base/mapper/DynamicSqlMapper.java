package com.mybatis.base.mapper;

import com.mybatis.base.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author panmingjie
 */
public interface DynamicSqlMapper {
    List<Employee> listEmployee(Employee employee);

    List<Employee> listEmployeeTrim(Employee employee);

    List<Employee> listEmployeeChoose(Employee employee);

    List<Employee> selectByCollection(@Param("ids") List<Integer> ids);

    int addEmployeeList(@Param("employees") List<Employee> employees);
}
