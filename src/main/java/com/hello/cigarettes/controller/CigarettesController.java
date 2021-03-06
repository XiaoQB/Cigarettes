package com.hello.cigarettes.controller;

import com.hello.cigarettes.domain.ResponseBean;
import com.hello.cigarettes.service.CigarettesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseBean<String> uploadExcelFile(@RequestParam("file") Object object) throws IOException {
        MultipartFile file = (MultipartFile) object;
            try{
                if(file != null){
                    System.out.println("asd");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            if(cigarettesService.saveFile(file)){
                return new ResponseBean<>(200, "success", null);
            }else{
                return new ResponseBean<>(400, "failed", null);
            }
       // return new ResponseBean<>(200, "success", null);
    }

    /**
     * 输入序列号，行号，列号获得摆放图片
     */
    @GetMapping
    public ResponseBean<Object> getArrangement(String id, int row, int  col) {
        return new ResponseBean<>(200, "success",cigarettesService.getArrangement(id, row, col));
    }

    @Autowired
    public void setAccountService(CigarettesService cigarettesService) {
        this.cigarettesService = cigarettesService;
    }

}
