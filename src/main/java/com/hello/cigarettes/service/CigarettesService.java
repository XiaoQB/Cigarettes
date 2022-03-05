package com.hello.cigarettes.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface CigarettesService {


    Boolean saveFile(MultipartFile file) throws IOException;

    String[][] getArrangement(String id, int row, int col);

}
