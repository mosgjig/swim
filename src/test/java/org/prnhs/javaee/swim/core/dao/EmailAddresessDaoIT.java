package org.prnhs.javaee.swim.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prnhs.javaee.swim.core.entity.EmailAddresess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith (SpringRunner.class )
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmailAddresessDaoIT {

    private static final Logger logger = LoggerFactory.getLogger(EmailAddresessDaoIT.class);
    
    @Autowired
    private EmailAddresessDao EmailAddresessDao;
    
    @PersistenceContext
    private EntityManager entityManager;

    private static final String email = "arbenhasimja"; 
    private EmailAddresess p;    
    
    @Before
    public void onStartUp() {
        p = new EmailAddresess() ;
        p.setEmail(email);           
    }
    
    @Test
    public void testSave() {
        EmailAddresessDao.save(p);
        assertNotNull(p.getId());
    }
         
    @Test
    public void testFindOne() {
        EmailAddresessDao.save(p);
        entityManager.flush();
        assertNotNull(p.getId());

        EmailAddresess savedEntity = (EmailAddresess) EmailAddresessDao.findOne(p.getId());

        assertNotNull(savedEntity);
        assertEquals(p, savedEntity);
    }
     @Test
    public void testFindAll() {
        EmailAddresessDao.save(p);
        Iterable<EmailAddresess> entities = EmailAddresessDao.findAll();
        assertTrue(entities.iterator().hasNext());
    }
@Test
    public void testUpdate() {
        EmailAddresessDao.save(p);
        entityManager.flush();
        assertNotNull(p.getId());
        EmailAddresess savedEntity = EmailAddresessDao.findOne(p.getId());
        assertEquals(p, savedEntity);

        savedEntity.setEmail("mimozahasimja");

        EmailAddresess updatedEntity = EmailAddresessDao.save(savedEntity);
        assertNotNull(updatedEntity);
        assertEquals(savedEntity, updatedEntity);
    }

    @Test
    public void testDelete() {

        EmailAddresessDao.save(p);
        entityManager.flush();
        assertNotNull(p.getId());
        EmailAddresessDao.delete(p.getId());
        EmailAddresess entity = EmailAddresessDao.findOne(p.getId());
        assertNull(entity);
    }
}

    
    

