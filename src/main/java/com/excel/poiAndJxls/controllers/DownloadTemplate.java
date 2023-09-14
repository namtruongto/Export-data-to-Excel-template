package com.excel.poiAndJxls.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excel.poiAndJxls.services.DownloadService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
public class DownloadTemplate {

    private final DownloadService downloadService;

    @GetMapping("/{templateName}")
    public void downloadTemplate(@PathVariable("templateName") String templateName, HttpServletResponse response) {
        this.downloadService.downloadTemplate(templateName, response);
    }
    
}
