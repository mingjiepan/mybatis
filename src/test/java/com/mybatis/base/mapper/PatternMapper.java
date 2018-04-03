package com.mybatis.base.mapper;

import com.mybatis.base.domain.Pattern;

/**
 * @author panmingjie
 */
public interface PatternMapper {
    Pattern selectByPrimary(Integer id);

    void insertByHandler(Pattern pattern);

    void insertByNoHandler(Pattern pattern);
}
