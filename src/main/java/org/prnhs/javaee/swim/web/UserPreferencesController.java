package org.prnhs.javaee.swim.web;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import java.util.List;
import javax.annotation.PostConstruct;
import org.prnhs.javaee.swim.dto.UserPreferencesDto;
import org.prnhs.javaee.swim.services.UserPreferencesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPreferencesController.class);

    @Autowired
    private MetricRegistry metrics;

    @Autowired
    private ConsoleReporter reporter;

    @Autowired
    private UserPreferencesService userPreferencesService;

    @PostConstruct
    private void startProfiler() {
        //    reporter.start(15, TimeUnit.SECONDS);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserPreferencesDto save(@RequestBody UserPreferencesDto dto) {
        LOGGER.debug("Save method has been called, saving username {}", dto.getUsername());
        Meter requests = metrics.meter("save");
        requests.mark();
        return userPreferencesService.save(dto);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserPreferencesDto getByUsername(@PathVariable String username) {
        LOGGER.debug("getByUsername method has been called with the username: {}", username);
        Meter requests = metrics.meter("getByUsername");
        requests.mark();
        return userPreferencesService.getByUsername(username);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserPreferencesDto> getAll() {
        LOGGER.debug("GetAll method has been called");
        Meter requests = metrics.meter("getAll");
        requests.mark();
        return userPreferencesService.getAll();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String username) {
        LOGGER.debug("Delete method with username {} has been called", username);
        userPreferencesService.delete(username);
    }

}
