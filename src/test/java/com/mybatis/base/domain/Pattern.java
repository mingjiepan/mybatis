package com.mybatis.base.domain;

import java.util.List;

/**
 * @author panmingjie
 */
public class Pattern {
    private Integer id;
    private List<String> indexs;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getIndexs() {
        return indexs;
    }

    public void setIndexs(List<String> indexs) {
        this.indexs = indexs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "id=" + id +
                ", indexs=" + indexs +
                ", address='" + address + '\'' +
                '}';
    }
}
