package com.hello.cigarettes.dao;

import com.hello.cigarettes.domain.Cigarette;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */
public class CigaretteDao {

    private CigaretteMapper cigaretteMapper;

    boolean insertCigarette(Cigarette cigarette){
        return cigaretteMapper.insertCigarette(cigarette);
    }

    List<Cigarette> getCigarettesBySellerId(int sellerId){
        return cigaretteMapper.getCigarettesBySellerId(sellerId);
    }

    @Autowired
    public void setCigaretteMapper(){
        this.cigaretteMapper = cigaretteMapper;
    }
}
