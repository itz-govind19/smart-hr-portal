package com.demo.departmentservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EnvParser {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${db2url}")
    private String dbUrl;

    @Value("${db2user}")
    private String dbUser;

    @Value("${db2password}")
    private String dbPassword;

}
