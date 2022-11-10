package com.example.spring_boot_config.controller;

import com.example.spring_boot_config.config.DBConfig;
import com.example.spring_boot_config.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: Linn Lat Htun
 * @created: 10/10/2022
 * @project: spring-boot-config
 * @package: com.example.spring_boot_config.controller
 */

@RestController
public class ConfigController {

    /*** Getting Value From application.properties or application.yml ***/
    @Value("${my.greeting}")
    private String greeting;

    /*** message is not declared in application.properties  So,add the value 'default' ***/
    @Value("${message:default}")
    private String defaultMessage;

    @Value("This is static message")
    private String staticMessage;

    @Value("${app.name}")
    private String appName;

    @Value("${app.description}")
    private String appDes;

    @Value("${my.list.values}")
    private List<String> numberList;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    /**
     * # is added because {} in application.properties.
     **/
    @Value("#{${db.connection}}")
    private Map<String, String> dbConnectionMap;

    @Value("${my.connection}")
    private String myConnection;

    /**
     * Better way using configuration annotation in DBConfig
     **/
    @Autowired
    DBConfig dbConfig;

    @Autowired
    Environment environment;

    @Value("${server.port}")
    int port;

    @Value("${message.valueFromMainConfig}")
    private String valueFromMainConfig;


    @GetMapping("/config")
    public Message helloConfig() {
        String space = "  ";

        /** Extract one value from List**/
        String one = numberList.get(0);

        System.out.println(one);
        System.out.println(staticMessage);
        System.out.println(dbConnectionMap);

        System.out.println(dbConfig.getConnection());
        System.out.println(dbConfig.getPort());
        System.out.println(dbConfig.getHost());

        Message message = new Message(greeting, activeProfile, dbConfig.getPort(), dbConfig.getConnection(), valueFromMainConfig);

        return message;
        //return greeting + space + appName + space + appDes + space + defaultMessage + space + numberList + space + myConnection;

    }

    /**
     * Accessing External Properties
     * (i)Export Jar File
     * (ii)create another application.properties file under target with vi command from terminal
     * (iii)write my.greeting =Hello from external application.properties
     * (iv)run java file (java -jar fileName) and then call the api and see the changes.
     *
     *
     * Another Way from commandLine Running
     * java -jar spring_boot_config-0.0.1-SNAPSHOT.jar --my.greeting="Hello From Command Line External."
     * that don't affect on application.properties under target jar file.
     * * */

    /**
     * Calling this api when you add the actuator dependency
     * http://localhost:8080/actuator/configprops
     *
     * and press ctl+f then search with keyword ConfigDb, you can see db properties that you created.
     * **/

    /**
     * yml file is as same as application.properties
     * create app.yml and replace = to :
     * yml file => key:value
     * key:
     *   key:value
     * **/

    /**
     * Using spring profile
     * eg. add value spring.profile.active=dev
     * **/

    /**
     * Using Environment, to know the environment that the project is running to call this api
     * http://localhost:8083/env
     * */

    @GetMapping("/env")
    public String envDetails() {
        System.out.println(environment);
        //System.out.println(environment.getProperty(null));
     return environment.toString();
    }


    /**
     * Business logic should not be used in profile
     * **/
}
