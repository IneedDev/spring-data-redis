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

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
