package com.excel.poiAndJxls.services;

import jakarta.servlet.http.HttpServletResponse;

public interface DownloadService {
    void downloadTemplate(String templateName, HttpServletResponse response);
}
