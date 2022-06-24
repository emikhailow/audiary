package com.ewm.diary.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "google-drive")
@Data
public class GoogleDriveProperties {
    private Folder folder;

    @Data
    public static class Folder{
        private String id;
    }
}
