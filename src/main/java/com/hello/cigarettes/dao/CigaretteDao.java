package com.hello.cigarettes.dao;

import com.hello.cigarettes.domain.Cigarette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */
@Repository
public class CigaretteDao {

    private CigaretteMapper mapper;

    public boolean insertCigarettes(List<Cigarette> cigarettes){
        return mapper.insertCigarettes(cigarettes);
    }

    public List<Cigarette> getCigarettesBySellerId(String sellerId){
        return mapper.getCigarettesBySellerId(sellerId);
    }

    @Autowired
    public void setCigaretteMapper(CigaretteMapper mapper){
        this.mapper = mapper;
    }
}
