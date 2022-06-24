package com.ewm.diary.googledriveapi;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.security.GeneralSecurityException;

@Component
public interface CloudDrive {
    String uploadFile(Path path) throws IOException, GeneralSecurityException;
}
