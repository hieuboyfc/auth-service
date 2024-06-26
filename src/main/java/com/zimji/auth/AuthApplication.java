package com.zimji.auth;

import com.zimji.auth.configuration.AuthProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.zimji.*"})
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
@EnableJpaRepositories({"com.zimji.*"})
@EntityScan(basePackages = {"com.zimji.*"})
@EnableConfigurationProperties(AuthProperties.class)
public class AuthApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
