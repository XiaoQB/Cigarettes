package com.hello.cigarettes.domain;

import lombok.Getter;


@Getter
public enum AccountRoleEnum {

    /**
     * 0 超级管理员 对所有的项目均可看
     * 1 只能看项目的所有数据
     */
    ADMIN(0),
    LEADER(1),
    MANAGER(1),
    DEVELOPER(2);

    private int right;

    AccountRoleEnum(int right){
        this.right = right;
    }

}
