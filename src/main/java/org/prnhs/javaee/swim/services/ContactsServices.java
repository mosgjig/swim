package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.dao.ContactsDao;
import org.prnhs.javaee.swim.core.entity.Contacts;
import org.prnhs.javaee.swim.dto.ContactsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ContactsServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsServices.class);

    @Autowired
    private ContactsDao dao;

    public ContactsDto save(ContactsDto contactsDto) {

        if (contactsDto.getId() == null) {

            LOGGER.warn("The exception is thrown as a result of a blank Contact Id");
            throw new IllegalArgumentException("Hey, you were supposed to give me a contact");
        }
        
        Contacts contacts = dao.findOne(contactsDto.getId());

        if (contacts == null) {
            contacts = ContactTranslator.toEntity(contactsDto);
        } else{
            contacts.setFirstName(contactsDto.getFirstName());
            contacts.setLastName(contactsDto.getLastName());
            contacts.setMiddleName(contactsDto.getMiddleName());
            contacts.setTitle(contactsDto.getTitle());
        }
        
        contacts = dao.save(contacts);
        
        ContactsDto savedContact = ContactTranslator.toDto(contacts);

        LOGGER.debug("Save method is executed successfully with the results: {}", savedContact);
        return savedContact;
    }

    public ContactsDto getById(Integer id){
        
        ContactsDto dto = null;
        Contacts contacts = dao.findOne(id);
        LOGGER.debug("The getById method is called", id);

        if(contacts != null){

            dto = ContactTranslator.toDto(contacts);
            LOGGER.debug("A Contact is found with the results: {}", dto);
        } else {
            LOGGER.debug("The Contact with a given Id of {} doesn't exist, the method getById returns null", id);
        }

        return dto;                
    }
    
    public List<ContactsDto> getAll(){
        
        Iterable<Contacts> contacts = dao.findAll();
        List<ContactsDto> dtos = new ArrayList<>();

        LOGGER.debug("The getAll method is called");

        if(contacts != null) {

            LOGGER.debug("Returned all the Contacts that were found");
            Iterator<Contacts> it = contacts.iterator();

            while (it.hasNext()) {
                Contacts c = it.next();
                ContactsDto dto = ContactTranslator.toDto(c);
                dtos.add(dto);
            }
        }
        
        return dtos;
    }
    
    public void delete(Integer id) {
        Contacts contacts = dao.findOne(id);
        LOGGER.debug("The delete method is called");

        if (contacts != null){

            LOGGER.debug("The Contact with the given Id of {} is deleted", id);
            dao.delete(contacts);
        }
    }
}