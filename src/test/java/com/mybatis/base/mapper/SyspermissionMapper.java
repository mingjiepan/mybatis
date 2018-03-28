package com.mybatis.base.mapper;

import com.mybatis.base.domain.Syspermission;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author panmingjie
 */
public interface SyspermissionMapper {
    List<Syspermission> list(String type);

    /*map的key为列名，value为列值*/
    Map<String, Object> map(int id);

    List<Map<String,Object>> listMap(String type);

    @MapKey("id")
    Map<Integer, Syspermission> mapObj();
}
