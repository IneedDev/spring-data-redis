package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Data
@Component
public class EnvHelper {
    private static LocalDateTime startDate = LocalDateTime.now();
    @Value("${app.name}")
    private String applicationName;

    @Value(value = "${logging.logLevel}")
    private String logLevel;

    @Value("${tomcat.maxThreads}")
    private Integer maxThreads;

    @Value("${server.port}")
    private Integer tomcatPort;

    @Value("${tomcat.minSpareThreads}")
    private Integer minSpareThreads;

    @Value("${tomcat.acceptCount}")
    private Integer acceptCount;

    @Value("${tomcat.maxConnections}")
    private Integer maxConnections;

    @Value("${tomcat.connectionTimeout}")
    private Integer connectionTimeout;

    @Value("${tomcat.keepAliveTimeout}")
    private Integer keepAliveTimeout;

    @Value("${tomcat.maxKeepAliveRequests}")
    private Integer maxKeepAliveRequests;
//
//    @Value("${kafka.consumer.bootstrapServers}")
//    private String bootstrapServers;

    public String getLocalAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "undefine";
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
