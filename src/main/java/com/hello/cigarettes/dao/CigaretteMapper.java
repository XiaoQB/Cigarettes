package com.hello.cigarettes.dao;

import com.hello.cigarettes.domain.Cigarette;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */
@Repository
public interface CigaretteMapper {

    boolean insertCigarettes(List<Cigarette> cigarettes);

    List<Cigarette> getCigarettesBySellerId(String sellerId);

    void deleteData();
}
