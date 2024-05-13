package com.courseproject.demo.controllers;

import com.courseproject.demo.dtos.FileDto;
import com.courseproject.demo.mappers.FileMapper;
import com.courseproject.demo.repositories.EventRepository;
import com.courseproject.demo.repositories.FileRepository;
import com.courseproject.demo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для работы с файлами.
 *
 * <p>
 * Автор: Korchanova
 * Версия: 1.0
 * </p>
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/file")
public class FileController {
    @Autowired
    FileService fileService;

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    @Autowired
    private EventRepository eventRepository;

    /**
     * Конструктор контроллера файлов.
     *
     * @param fileRepository Репозиторий файлов.
     * @param fileMapper     Маппер файлов.
     */
    public FileController(FileRepository fileRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    /**
     * Получение списка всех файлов.
     *
     * @return Список DTO файлов.
     */
    @GetMapping("/all")
    public List<FileDto> getAllFiles() {
        return fileRepository.findAll().stream().map(fileMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Поиск файла по имени.
     *
     * @param name Имя файла.
     * @return Список DTO найденных файлов.
     */
    @GetMapping("/find/{name}")
    public List<FileDto> findFile(@PathVariable String name) {
        return fileRepository.findByName(name).stream().map(fileMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Загрузка файла.
     *
     * @param file Загружаемый файл.
     * @return Результат загрузки файла.
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(value = "file",required = false) MultipartFile file) {
        try {
            fileService.upload(file);
            return "OK";

        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be uploaded");
        }
    }

    /**
     * Загрузка данных о файле.
     *
     * @param fileDto Данные о файле.
     * @return DTO загруженного файла.
     */
    @PostMapping("/uploadFileData")
    public FileDto uploadFileData(@RequestBody FileDto fileDto) {
        return fileMapper.toDto(fileRepository.save(fileMapper.toEntity(fileDto)));
    }

    /**
     * Загрузка файла.
     *
     * @param name Имя файла.
     * @return Ответ с загруженным файлом.
     */
    @GetMapping("/download/{name}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String name) {
        Resource resource = null;
        try {
            resource = fileService.download(name);
            String contentType = "application/octet-stream";
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } catch (IOException e) {
            throw new IllegalArgumentException("File could not be downloaded");
        }
    }
}
