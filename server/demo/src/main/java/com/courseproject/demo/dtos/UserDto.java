package com.courseproject.demo.dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.courseproject.demo.entities.User}
 */
public class UserDto implements Serializable {
    private final Integer id;
    private final String login;
    private final String password;

    public UserDto(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.login, entity.login) &&
                Objects.equals(this.password, entity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "login = " + login + ", " +
                "password = " + password + ")";
    }
}