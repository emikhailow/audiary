package com.ewm.diary.configs;

import com.ewm.diary.properties.GoogleDriveProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableConfigurationProperties(
        GoogleDriveProperties.class
)
@RequiredArgsConstructor
@EnableScheduling
public class AppConfig {
}
