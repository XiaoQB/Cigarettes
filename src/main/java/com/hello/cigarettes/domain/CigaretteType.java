package com.hello.cigarettes.domain;

/**
 * @author XiaoQuanbin
 * @date 2022/3/4
 */
public enum CigaretteType {

    NORMAL("常规"),
    THIN("细支"),
    MIDDLE("中支");


    private final String type;

    CigaretteType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
