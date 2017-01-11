package org.prnhs.javaee.swim.services.config;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.dozer.classmap.RelationshipType;
import org.dozer.loader.api.BeanMappingBuilder;
import static org.dozer.loader.api.FieldsMappingOptions.collectionStrategy;
import static org.dozer.loader.api.FieldsMappingOptions.oneWay;
import org.prnhs.javaee.swim.dto.MyObjectA;
import org.prnhs.javaee.swim.dto.MyObjectB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    private static final String PASSWORD = "password";
    private static final String KEY = "myKey";
    private static final String ID = "myId";
    private static final String MY_LIST = "myList";
    private static final String MY_USERS = "myUsers";

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
        mapper.addMapping(mappings());
        return mapper;
    }

    public BeanMappingBuilder mappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(MyObjectA.class, MyObjectB.class)
                        .fields(KEY, ID)
                        .fields(PASSWORD, PASSWORD, oneWay())
                        .fields(MY_LIST, MY_LIST, collectionStrategy(Boolean.TRUE, RelationshipType.NON_CUMULATIVE))
                        .fields(MY_USERS, MY_USERS, collectionStrategy(Boolean.FALSE, RelationshipType.NON_CUMULATIVE));
                
            }
        };
    }
}
