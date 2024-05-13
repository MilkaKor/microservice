package com.courseproject.demo.mappers;

import com.courseproject.demo.dtos.FileDto;
import com.courseproject.demo.entities.File;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {EventFileMapper.class})
public interface FileMapper {
    File toEntity(FileDto fileDto);

    FileDto toDto(File file);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    File partialUpdate(FileDto fileDto, @MappingTarget File file);
}