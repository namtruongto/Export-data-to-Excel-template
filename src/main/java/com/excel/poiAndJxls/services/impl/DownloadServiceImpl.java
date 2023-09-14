package com.excel.poiAndJxls.services.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import com.excel.poiAndJxls.exception.NotFoundException;
import com.excel.poiAndJxls.services.DownloadService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DownloadServiceImpl implements DownloadService{

    @Override
    public void downloadTemplate(String templateName, HttpServletResponse response) {
        try(InputStream inputStream = getClass().getResourceAsStream("/templates/" + templateName)) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + templateName);
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (NullPointerException e) {
            log.error("Not found template: " + templateName + " in resources/templates");
            throw new NotFoundException("Not found template: " + templateName + " in resources/templates");
        } catch (IOException e) {
            log.error("Error when download template: " + templateName, e);
            throw new RuntimeException("Error when download template: " + templateName, e);
        }
    }


}
