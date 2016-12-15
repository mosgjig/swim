package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.prnhs.javaee.swim.core.dao.UserPreferencesDao;
import org.prnhs.javaee.swim.core.entity.UserPreferences;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferencesService {

    @Autowired
    private UserPreferencesDao dao;

    public UserPreferencesDto save(UserPreferencesDto userPreferencesDto) {
        if (userPreferencesDto == null) {
            throw new IllegalArgumentException("Please, give me a preference");
        }

        UserPreferences userPreferences = dao.findOne(userPreferencesDto.getUsername());

        if (userPreferences == null) {
            userPreferences = UserPreferencesTranslator.toEntity(userPreferencesDto);

        } else {
            userPreferences.setUsername(userPreferencesDto.getUsername());
            userPreferences.setTypeId(userPreferencesDto.getTypeId());
        }

        userPreferences = dao.save(userPreferences);

        UserPreferencesDto savedUserPreferences = UserPreferencesTranslator.toDto(userPreferences);

        return savedUserPreferences;

    }

    public UserPreferencesDto getByUsername(String username) {
        UserPreferencesDto dto = null;
        UserPreferences userPreferences = dao.findOne(username);
        if (userPreferences != null) {
            dto = UserPreferencesTranslator.toDto(userPreferences);
        }
        return dto;
    }

    public List<UserPreferencesDto> getAll() {
        Iterable<UserPreferences> userPreferences = dao.findAll();
        List<UserPreferencesDto> dtos = new ArrayList<>();
        if (userPreferences != null) {
            Iterator<UserPreferences> it = userPreferences.iterator();

            while (it.hasNext()) {
                UserPreferences up = it.next();
                UserPreferencesDto dto = UserPreferencesTranslator.toDto(up);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public void delete(String username) {
        UserPreferences userPreferences = dao.findOne(username);
        if (userPreferences != null) {
            dao.delete(userPreferences);
        }

    }

}
