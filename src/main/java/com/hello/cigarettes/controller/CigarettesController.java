package com.hello.cigarettes.controller;

import com.hello.cigarettes.service.CigarettesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;


@RestController
@CrossOrigin
@RequestMapping("/cigarettes")
public class CigarettesController {

    private CigarettesService cigarettesService;


    /**
     * 上传文件到指定路径
     */
    @PostMapping
    public String uploadExcelFile( ) throws IOException {
            if(cigarettesService.saveFile()){
                return "success";
            }else{
                return "failed";
            }
    }

    /**
     * 输入序列号，行号，列号获得摆放图片
     */
    @GetMapping
    public Object getArrangement(String id, int raw, int  col) {
        return cigarettesService.getArrangement(id, raw, col);
    }

    @Autowired
    public void setAccountService(CigarettesService cigarettesService) {
        this.cigarettesService = cigarettesService;
    }

}
