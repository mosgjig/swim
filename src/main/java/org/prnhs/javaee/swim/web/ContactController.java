package org.prnhs.javaee.swim.web;

import org.prnhs.javaee.swim.dto.ContactsDto;
import org.prnhs.javaee.swim.services.ContactsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactsServices service;

    @RequestMapping(value = "/",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactsDto save(@RequestBody ContactsDto dto) {
        return service.save(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactsDto getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactsDto> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
