package com.ewm.diary.controllers;

import com.ewm.diary.entites.Record;
import com.ewm.diary.googledriveapi.CloudDrive;
import com.ewm.diary.services.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final RecordService recordService;
    private final CloudDrive cloudDrive;

    @GetMapping("/records")
    public List<Record> getRecords(){
        return recordService.findAll();
    }

    @DeleteMapping("/records/{id}")
    public void deleteRecord(@PathVariable Long id)
    {
        recordService.delete(id);
    }

    @PostMapping("/save")
    public void saveRecord(HttpServletRequest request) throws IOException, GeneralSecurityException {
        byte[] bytes = retrieveBytesFromRequest(request);
        Path path = saveAndGetPath(bytes);
        Record record = new Record(path.getFileName().toString());
        record.setGoogleDriveId(cloudDrive.uploadFile(path));
        recordService.save(record);
    }

    private byte[] retrieveBytesFromRequest(HttpServletRequest request) throws IOException {
        return ((StandardMultipartHttpServletRequest) request).getFiles("voice").get(0).getBytes();
    }

    private Path saveAndGetPath(byte[] bytes) throws IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String fileName = String.format("%s.webm", simpleDateFormat.format(new Date()));
        Path filepath = Paths.get(System.getProperty("user.dir") + "\\records", fileName);
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(bytes);
        }
        return filepath;

    }
}
