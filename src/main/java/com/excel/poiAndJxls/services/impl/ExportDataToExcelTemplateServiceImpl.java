package com.excel.poiAndJxls.services.impl;

import com.excel.poiAndJxls.dtos.Server;
import com.excel.poiAndJxls.services.ExportDataToExcelTemplateService;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportDataToExcelTemplateServiceImpl implements ExportDataToExcelTemplateService {

    @Override
    public void exportDataToExcelTemplate(OutputStream outputStream) {

        List<Server> data = initializeData();

        Map<String, Object> servers = new HashMap<>();
        servers.put("data", data);

        try(InputStream inputStream = this.getClass().getResourceAsStream("/template_exports/template_server_list_for_export.xlsx")) {
            Context context = new Context();
            context.toMap().putAll(servers);
            JxlsHelper.getInstance().processTemplate(inputStream, outputStream, context);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Server> initializeData() {
        // generate data by loop 10 lần
        List<Server> servers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Server server = Server.builder()
                    .sourceIp("192.168.1." + i)
                    .system("Linux " + i)
                    .subSystem1("subSystem1 " + i)
                    .subSystem2("subSystem2 " + i)
                    .cpu("Intel " + i)
                    .ram("8GB " + i)
                    .os("CentOS " + i)
                    .site("HCM " + i)
                    .hostName("hostName " + i)
                    .owner("owner " + i)
                    .status("status " + i)
                    .grantTime("grantTime " + i)
                    .note("note " + i)
                    .build();
            servers.add(server);
        }
        return servers;
    }
}
