package com.hello.cigarettes.service;

import java.io.File;
import java.io.IOException;

public interface CigarettesService {


    Boolean saveFile( ) throws IOException;

    String getArrangement(String id, int row, int col);

}
