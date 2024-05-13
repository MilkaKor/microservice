package com.courseproject.demo.repositories;

import com.courseproject.demo.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findByName(String name);
}