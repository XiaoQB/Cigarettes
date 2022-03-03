package com.hello.cigarettes.dao;

import com.hello.cigarettes.domain.Cigarette;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */
public interface CigaretteMapper {
    @Insert("insert into cigarette (seller_id, seller_name, cigarette_name, order_num, price, cigarette_type) " +
            "values (#{},#{},#{},#{},#{},#{})")
    boolean insertCigarette(Cigarette cigarette);

    @Select("select * from cigarettes where seller_id = #{sellerId}")
    List<Cigarette> getCigarettesBySellerId(int sellerId);
}
