package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.entity.User;
import org.prnhs.javaee.swim.dto.UserDto;

public class UserTranslator {

    public static UserDto toDto(User user) {
        if (user == null) {
            throw new IllegalArgumentException("hey, you were supposed to give me a user!");
        }

        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEnabled(user.getEnabled());

        return dto;
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("hey, you were supposed to give me a user!");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEnabled(dto.getEnabled());
        return user;
    }
}
