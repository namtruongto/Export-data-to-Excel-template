package com.excel.poiAndJxls.controllers;

import com.excel.poiAndJxls.services.ExportDataToExcelTemplateService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportDataToExcelTemplateFile {

    private final ExportDataToExcelTemplateService exportDataToExcelTemplateService;

    @GetMapping
    public void exportDataToExcelTemplateFile(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=exportDataToExcelTemplate.xlsx");
            this.exportDataToExcelTemplateService.exportDataToExcelTemplate(response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
