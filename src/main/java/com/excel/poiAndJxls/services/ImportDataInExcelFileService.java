package com.excel.poiAndJxls.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.excel.poiAndJxls.dtos.Server;

public interface ImportDataInExcelFileService {
    List<Server> importDataInExcelFile(MultipartFile file);
}
