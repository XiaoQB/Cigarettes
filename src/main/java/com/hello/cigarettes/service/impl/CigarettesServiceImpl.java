package com.hello.cigarettes.service.impl;


import com.hello.cigarettes.service.CigarettesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class CigarettesServiceImpl implements CigarettesService {

    private CigarettesService cigarettesService;


    @Autowired
    public void setAccountService(CigarettesService cigarettesService) {
        this.cigarettesService = cigarettesService;
    }

    @Override
    public Boolean saveFile(File excel) {
        return null;
    }

    @Override
    public String getArrangement(String id, int row, int col) {
        return null;
    }
}
