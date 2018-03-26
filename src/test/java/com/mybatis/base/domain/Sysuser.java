package com.mybatis.base.domain;

/**
 * @author panmingjie
 */
public class Sysuser {
    private String id;
    private String username;
    private String usercode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    @Override
    public String toString() {
        return "Sysuser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", usercode='" + usercode + '\'' +
                '}';
    }
}
