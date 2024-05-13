package com.courseproject.demo.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.courseproject.demo.entities.File}
 */
public class FileDto implements Serializable {
    private final Integer id;
    @NotNull
    private final EventFileDto event;
    @NotNull
    private final String name;

    public FileDto(Integer id, EventFileDto event, String name) {
        this.id = id;
        this.event = event;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public EventFileDto getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDto entity = (FileDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.event, entity.event) &&
                Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "event = " + event + ", " +
                "name = " + name + ")";
    }
}