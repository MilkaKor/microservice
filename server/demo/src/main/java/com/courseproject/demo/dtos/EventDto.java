package com.courseproject.demo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.courseproject.demo.entities.Event}
 */
public class EventDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 250)
    private final String name;
    @NotNull
    private final String description;
    private final Set<FileEventDto> files;

    public EventDto(Integer id, String name, String description, Set<FileEventDto> files) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.files = files;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<FileEventDto> getFiles() {
        return files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto entity = (EventDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.files, entity.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, files);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "files = " + files + ")";
    }
}