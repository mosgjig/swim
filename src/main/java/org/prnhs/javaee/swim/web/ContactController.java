package org.prnhs.javaee.swim.web;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.prnhs.javaee.swim.dto.ContactsDto;
import org.prnhs.javaee.swim.services.ContactsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private MetricRegistry metrics;

    @Autowired
    private ConsoleReporter reporter;

    @Autowired
    private ContactsServices service;

    @PostConstruct
    private void startProfiler(){
//      reporter.start(15, TimeUnit.SECONDS);
    }

    @RequestMapping(value = "/",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactsDto save(@RequestBody ContactsDto dto) {
        LOGGER.debug("A request to POST method saving '{}' as a Contact", dto.getFirstName());
        Meter requests = metrics.meter("save");
        requests.mark();
        return service.save(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactsDto getById(@PathVariable Integer id){

        LOGGER.debug("A request to the GET method, returning a Contact by given ID of {} ", id);
        Meter requests = metrics.meter("getById");
        requests.mark();
        return service.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactsDto> getAll() {
        LOGGER.debug(" A request to the GET method, returning all Contacts");
        Meter requests = metrics.meter("getAll");
        requests.mark();
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        LOGGER.debug(" A request to a DELETE method with a given Id of {} ", id);
        Meter requests = metrics.meter("delete");
        requests.mark();
        service.delete(id);
    }
}
