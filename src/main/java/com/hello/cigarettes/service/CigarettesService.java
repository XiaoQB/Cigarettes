package com.hello.cigarettes.service;

import java.io.File;

public interface CigarettesService {


    Boolean saveFile(File excel);

    String getArrangement(String id, int row, int col);



}
