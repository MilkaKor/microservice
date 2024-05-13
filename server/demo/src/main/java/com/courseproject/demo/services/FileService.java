package com.courseproject.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class FileService {
    @Autowired
    private ResourceLoader resourceLoader;

    public void upload(MultipartFile file) throws IOException {
        File tempFile = new File(resourceLoader.getResource("classpath:store/").getFile() + "/" + file.getOriginalFilename());
        System.out.println(resourceLoader.getResource("classpath:store/").getFile() + "/" + file.getOriginalFilename());
        if (tempFile.createNewFile()) System.out.println("File created");
        else System.out.println("File already exists");
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
        stream.write(file.getBytes());
        stream.close();
    }

    public Resource download(String filename) throws IOException {
        final Resource resource = resourceLoader.getResource("classpath:store/" + filename);
        return resource;
    }

    public void delete(String filename) throws IOException {
        File file = new File(String.valueOf(resourceLoader.getResource("classpath:store/" + filename).getFile()));
        file.delete();
    }
}
