
package org.prnhs.javaee.swim.core.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.prnhs.javaee.swim.core.dao.ContactsDao;
import org.prnhs.javaee.swim.core.dto.ContactsDto;
import org.prnhs.javaee.swim.core.entity.Contacts;
import org.prnhs.javaee.swim.services.ContactsServices;

public class ContactsServiceIT {
    
    @InjectMocks
    private ContactsServices service;
    
    @Mock
    private ContactsDao dao;
    
    private ContactsDto dto;
    private Contacts contacts;
    
}
