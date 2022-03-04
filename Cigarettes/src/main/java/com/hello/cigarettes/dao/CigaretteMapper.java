package com.hello.cigarettes.dao;

import com.hello.cigarettes.domain.Cigarette;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */
@Repository
public interface CigaretteMapper {

    boolean insertCigarettes(List<Cigarette> cigarettes);

    @Select("select * from cigarettes where seller_id = #{sellerId}")
    List<Cigarette> getCigarettesBySellerId(int sellerId);
}
