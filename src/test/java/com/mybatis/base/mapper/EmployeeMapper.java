package com.mybatis.base.mapper;

import com.mybatis.base.domain.Employee;

/**
 * @author panmingjie
 */
public interface EmployeeMapper {
    Employee selectEmployee(Integer id);

    Employee selectById(Integer id);

    Employee selectByPrimary(Integer id);
}
