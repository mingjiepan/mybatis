package com.mybatis.base.domain;

/**
 * @author panmingjie
 */
public class Employee {
    private Integer id;
    private Integer departId;
    private String name;
    private String address;

    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", departId=" + departId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", department=" + department +
                '}';
    }
}
