package com.example.spring_boot_config.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Linn Lat Htun
 * @created: 10/10/2022
 * @project: spring-boot-config
 * @package: com.example.spring_boot_config.model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String msgTitle;
    private String activeProfile;
    private int serverPort;
    private String msgDescription;
    private String valueFromMainConfig;
}
