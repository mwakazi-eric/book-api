package com.book.api.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) {
        String filename = file.getOriginalFilename();

        String filePath = path + File.separator + filename;
        // images/my_file.txt
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        // copy file content to path

            CompletableFuture.runAsync(() -> {
                try {
                    Files.copy(file.getInputStream(), Paths.get(filePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        return filename;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath);
    }
}
