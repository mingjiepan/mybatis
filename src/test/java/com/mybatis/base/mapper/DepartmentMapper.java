package com.mybatis.base.mapper;

import com.mybatis.base.domain.Department;

/**
 * @author panmingjie
 */
public interface DepartmentMapper {
    Department selectById(Integer id);


    Department selectDepAndEmpById(Integer id);

    Department selectByStep(Integer id);
}
