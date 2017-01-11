package org.prnhs.javaee.swim.services.config;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
        return mapper;
    }
}
