package com.mybatis.base.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panmingjie
 */
public class Department {
    private Integer id;
    private String departName;
    private String detail;

    List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departName='" + departName + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
