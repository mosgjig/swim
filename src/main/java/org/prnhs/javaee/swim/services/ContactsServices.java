package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.dao.ContactsDao;
import org.prnhs.javaee.swim.core.entity.Contacts;
import org.prnhs.javaee.swim.dto.ContactsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ContactsServices {

    @Autowired
    private ContactsDao dao;

    public ContactsDto save(ContactsDto contactsDto) {

        if (contactsDto == null) {
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
        
        return savedContact;
    }

    public ContactsDto getById(Integer id){
        
        ContactsDto dto = null;
        Contacts contacts = dao.findOne(id);

        if(contacts != null){
            dto = ContactTranslator.toDto(contacts);
        }
        
        return dto;                
    }
    
    public List<ContactsDto> getAll(){
        
        Iterable<Contacts> contacts = dao.findAll();
        List<ContactsDto> dtos = new ArrayList<>();

        if(contacts != null) {
            Iterator<Contacts> it = contacts.iterator();

            while (it.hasNext()) {
                Contacts c = it.next();
                ContactsDto dto = ContactTranslator.toDto(c);
                dtos.add(dto);
            }

//            contacts.forEach(contacts -> dtos.add(ContactTranslator.toDto(contacts)));
        }
        
        return dtos;
    }
    
    public void delete(Integer id) {
        Contacts contacts = dao.findOne(id);

        if (contacts != null){
            dao.delete(contacts);
        }
    }
}