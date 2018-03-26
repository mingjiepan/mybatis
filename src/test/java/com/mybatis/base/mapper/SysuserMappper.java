package com.mybatis.base.mapper;

import com.mybatis.base.domain.Sysuser;

/**
 * @author panmingjie
 */
public interface SysuserMappper {
    Sysuser selectUserById(int id);
}
