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
import org.prnhs.javaee.swim.core.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AddressDaoIT {
    private static final Logger logger = LoggerFactory.getLogger(AddressDaoIT.class);
    
    @Autowired
    private AddressDao addressDao;
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final String LINE1 = "some line1";
    private static final String LINE2 = "some line2";
    private static final String CITY = "city";
    private static final String COUNTRY_CODE = "country";
    private static final String POSTAL_CODE = "postal";
    
    private Address a;
    
    @Before
    public void onStartUp(){
        a = new Address();
        a.setLine1(LINE1);
        a.setLine2(LINE2);
        a.setCity(CITY);
        a.setCountryCode(COUNTRY_CODE);
        a.setPostalCode(POSTAL_CODE);
        
        
    }
    
    @Test
    public void testSave(){
        addressDao.save(a);
        assertNotNull(a.getLine1());
    }
    
//
//    @Test(expected = JpaSystemException.class)
//    public void testSave_NullAddresses(){
//        a.setLine1(null);
//        addressDao.save(a);
//    }
    
    @Test
    public void testFindOne(){
        addressDao.save(a);
        entityManager.flush();
        assertNotNull(a.getLine1());
        
        Address savedEntity = addressDao.findOne(a.getId());
        
        assertNotNull(savedEntity);
        assertEquals(a, savedEntity);
    }
    
    @Test
    public void testFindAll(){
        addressDao.save(a);
        Iterable<Address> entities = addressDao.findAll();
        assertTrue(entities.iterator().hasNext());
    }
    
    @Test
    public void testUpdate() {
        addressDao.save(a);
        entityManager.flush();
        assertNotNull(a.getId());
        Address savedEntity = addressDao.findOne(a.getId());
        assertEquals(a, savedEntity);

        savedEntity.setLine1("another Line1");
        savedEntity.setLine2("another Line2" );

        Address updatedEntity = addressDao.save(savedEntity);
        assertNotNull(updatedEntity);
        assertEquals(savedEntity, updatedEntity);
    }
    @Test
    public void testDelete(){
        
        addressDao.save(a);
        entityManager.flush();
        assertNotNull(a.getId());
        addressDao.delete(a.getId());
        Address entity = addressDao.findOne(a.getId());
        assertNull(entity);
    }
   
}
