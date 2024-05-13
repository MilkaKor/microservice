package com.courseproject.demo.mappers;

import com.courseproject.demo.dtos.FileEventDto;
import com.courseproject.demo.entities.File;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileEventMapper {
    File toEntity(FileEventDto fileEventDto);

    FileEventDto toDto(File file);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    File partialUpdate(FileEventDto fileEventDto, @MappingTarget File file);
}