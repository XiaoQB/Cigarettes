package com.hello.cigarettes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */
@Data
@AllArgsConstructor
public class Cigarette {
    private String sellerId;
    private String sellerName;
    private String cigaretteName;
    private int price;
    private int orderNum;
    private String type;
}
