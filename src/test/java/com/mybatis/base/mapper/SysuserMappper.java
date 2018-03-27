package com.mybatis.base.mapper;

import com.mybatis.base.domain.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author panmingjie
 */
public interface SysuserMappper {
    Sysuser selectUserById(int id);

    int insertUser(Sysuser sysuser);

    void update(Sysuser sysuser);

    void delete(String id);

    Sysuser findUserByIdAndUsername(@Param("id") Integer id, @Param("username") String username);

    Sysuser findUserByUsercodeAndUsername(Map<?, ?> map);
}
