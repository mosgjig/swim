package org.prnhs.javaee.swim.web;

import java.util.List;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;
import org.prnhs.javaee.swim.services.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userPreferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService userPreferencesService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserPreferencesDto save(@RequestBody UserPreferencesDto dto) {
        return userPreferencesService.save(dto);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserPreferencesDto getByUsername(@PathVariable String username) {
        return userPreferencesService.getByUsername(username);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserPreferencesDto> getAll() {
        return userPreferencesService.getAll();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String username) {
        userPreferencesService.delete(username);
    }

}
