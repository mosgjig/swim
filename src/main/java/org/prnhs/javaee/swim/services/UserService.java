package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.prnhs.javaee.swim.core.dao.UserDao;
import org.prnhs.javaee.swim.core.entity.User;
import org.prnhs.javaee.swim.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public UserDto save(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("hey, you were supposed to give me a user!");
        }

        User user = dao.findOne(userDto.getUsername());

        if (user == null) {
            user = UserTranslator.toEntity(userDto);

        } else {
            user.setPassword(userDto.getPassword());
            user.setEnabled(userDto.getEnabled());
        }

        user = dao.save(user);

        UserDto savedUser = UserTranslator.toDto(user);

        return savedUser;
    }

    public UserDto getById(String id) {
        UserDto dto = null;
        User user = dao.findOne(id);
        if (user != null) {
            dto = UserTranslator.toDto(user);
        }
        return dto;
    }

    public List<UserDto> getAll() {

        Iterable<User> users = dao.findAll();
        Iterator<User> it = users.iterator();
        List<UserDto> dtos = new ArrayList<>();
        while (it.hasNext()) {
            User u = it.next();
            UserDto dto = UserTranslator.toDto(u);
            dtos.add(dto);
        }
        return dtos;
    }

    public void delete(String id) {
        User user = dao.findOne(id);
        if (user != null) {
            user.setEnabled(Boolean.FALSE);
            dao.save(user);
        }
    }
}
