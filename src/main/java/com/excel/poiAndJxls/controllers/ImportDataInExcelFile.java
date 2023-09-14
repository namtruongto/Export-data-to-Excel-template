package com.excel.poiAndJxls.controllers;

import com.excel.poiAndJxls.dtos.Server;
import com.excel.poiAndJxls.services.ImportDataInExcelFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class ImportDataInExcelFile {

    private final ImportDataInExcelFileService importDataInExcelFileService;

    @PostMapping
    public ResponseEntity<?> importExcel(@RequestBody MultipartFile file) {
        List<Server> servers = this.importDataInExcelFileService.importDataInExcelFile(file);
        return ResponseEntity.ok(servers);
    }
    
}
