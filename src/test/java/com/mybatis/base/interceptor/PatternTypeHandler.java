package com.mybatis.base.interceptor;

import com.mybatis.base.domain.Pattern;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author panmingjie
 */
public class PatternTypeHandler implements TypeHandler<List> {
    @Override
    public void setParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
        StringBuffer stringBuffer = new StringBuffer();
        parameter.forEach(item -> {
            stringBuffer.append(item + ",");
        });
        String str = stringBuffer.toString();
        String substring = str.substring(0, str.length() - 1);
        ps.setString(i, substring);
    }

    @Override
    public List getResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        String[] split = string.split(",");
        List<String> strings = Arrays.asList(split);
        return strings;
    }

    @Override
    public List getResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        String[] split = string.split(",");
        List<String> strings = Arrays.asList(split);
        return strings;
    }

    @Override
    public List getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
