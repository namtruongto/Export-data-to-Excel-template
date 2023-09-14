package com.excel.poiAndJxls.services.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.excel.poiAndJxls.dtos.Server;
import com.excel.poiAndJxls.services.ImportDataInExcelFileService;

@Service
public class ImportDataInExcelFileServiceImpl implements ImportDataInExcelFileService {

    @Override
    public List<Server> importDataInExcelFile(MultipartFile file) {
        // Check if file is empty
        if (file == null) {
            throw new NullPointerException("File is empty");
        }

        // Check if the file is an Excel file xlsx
        if (!"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType())) {
            // Get extension file
            String extension = "";
            String fileName = file.getOriginalFilename();
            if (fileName != null && !fileName.isEmpty()) {
                extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
            throw new InvalidMediaTypeException(extension,"Invalid file format. Please upload an excel file extension xlsx");
        }

        // Check size file is less than 10MB
        if (file.getSize() > 10485760) {
            throw new MaxUploadSizeExceededException(10L* 1024 * 1024);
        }

        // Read data from Excel file
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // Check template is valid

            //1. check not empty
            Row headerRow = sheet.getRow(1);
            if (headerRow == null) {
                throw new RuntimeException("Template is invalid");
            }
            //2. check header is valid
            final String[] header = {"Source IP", "System", "Sub System 1", "Sub System 2", "CPU", "RAM", "OS", "Site", "Host Name", "Owner", "Status", "Grant Time", "Note"};
            // final String[] header = {"IP nguồn", "Hệ thống", "Phân hệ 1", "Phân hệ 2", "CPU", "RAM", "OS", "Site", "Hostname", "Owner", "Trạng thái", "Thời gian cấp", "Ghi chú"};
            for (int i = 0; i < header.length; i++) {
                if (!header[i].equals(getCellValue(headerRow.getCell(i+1)).toString().trim())) { // getCell(i+1) because the first column is No.
                    System.out.println(getCellValue(headerRow.getCell(i+1)).toString());
                    throw new RuntimeException("Template is invalid");
                }
            }

            // Read data from Excel file
            List<Server> servers = new ArrayList<>();
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 2; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                Server server = convertRowToServer(row);
                if(server != null)  servers.add(server);
            }
            workbook.close();
            return servers;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Server convertRowToServer(Row row) {
        if(row != null) {
            Server server = new Server();
            server.setSourceIp(getCellValue(row.getCell(1)).toString());
            server.setSystem(getCellValue(row.getCell(2)).toString());
            server.setSubSystem1(getCellValue(row.getCell(3)).toString());
            server.setSubSystem2(getCellValue(row.getCell(4)).toString());
            server.setCpu(getCellValue(row.getCell(5)).toString());
            server.setRam(getCellValue(row.getCell(6)).toString());
            server.setOs(getCellValue(row.getCell(7)).toString());
            server.setSite(getCellValue(row.getCell(8)).toString());
            server.setHostName(getCellValue(row.getCell(9)).toString());
            server.setOwner(getCellValue(row.getCell(10)).toString());
            server.setStatus(getCellValue(row.getCell(11)).toString());
            server.setGrantTime(getCellValue(row.getCell(12)).toString());
            server.setNote(getCellValue(row.getCell(13)).toString());
            return server;
        }
        return null;
    }

    private Object getCellValue(Cell cell) {
        // Check cell is empty
        if (cell == null) {
            return null;
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue();
                }
                yield cell.getNumericCellValue();
            }
            case FORMULA -> cell.getCellFormula();
            case ERROR -> cell.getErrorCellValue();
            default -> null;
        };
    }

}
