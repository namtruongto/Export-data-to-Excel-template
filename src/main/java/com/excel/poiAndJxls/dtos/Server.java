package com.excel.poiAndJxls.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Server {
    private String sourceIp;
    private String system;
    private String subSystem1;
    private String subSystem2;
    private String cpu;
    private String ram;
    private String os;
    private String site;
    private String hostName;
    private String owner;
    private String status;
    private String grantTime;
    private String note; 
}
