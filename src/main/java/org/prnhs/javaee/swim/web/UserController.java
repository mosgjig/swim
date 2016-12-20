package org.prnhs.javaee.swim.web;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.prnhs.javaee.swim.dto.UserDto;
import org.prnhs.javaee.swim.services.UserService;
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
@RequestMapping("/users")
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MetricRegistry metrics;
    
    @Autowired
    private ConsoleReporter reporter;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void startProfiler() {
//        reporter.start(15, TimeUnit.SECONDS);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto save(@RequestBody UserDto dto) {
        Meter requests = metrics.meter("save");
        requests.mark();
        return userService.save(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getById(@PathVariable String id) {
        
        LOGGER.debug("hello from getById {} ", id);
        Meter requests = metrics.meter("getById");
        requests.mark();
        return userService.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAll() {
        Meter requests = metrics.meter("getAll");
        requests.mark();
        return userService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
