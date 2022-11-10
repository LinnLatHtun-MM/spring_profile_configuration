package com.example.spring_boot_config.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Linn Lat Htun
 * @created: 10/10/2022
 * @project: spring-boot-config
 * @package: com.example.spring_boot_config.config
 */

/**
 * This is More Suitable, You don't need to create many @Value({"variable from application.properties"})
 * By Using @Configuration Annotation and write getter setter method.
 */

@Configuration
@ConfigurationProperties("db")
public class DBConfig {
    private String connection;
    private String host;

    /**
     * If the port type is integer, so we notice the conversion of data type.
     **/
    private int port;

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
