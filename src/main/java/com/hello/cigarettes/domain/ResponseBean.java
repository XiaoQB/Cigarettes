package com.hello.cigarettes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: keyon
 * @time: 2022/3/5 4:06 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean<T> implements Serializable {

    private int code;

    private String msg;

    private T data;
}
