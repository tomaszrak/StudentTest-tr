package com.prz.testing.enumerate;

/**
 * Created by Roman on 16.09.2015.
 */
public enum RoleName {

    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    ADMIN("ADMIN");

    private String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }
}
