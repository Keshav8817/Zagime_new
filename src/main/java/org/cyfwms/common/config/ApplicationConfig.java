package org.cyfwms.common.config;

import org.cyfwms.common.exception.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Bean
    public MessageUtil messageUtil(MessageSource messageSource) {
        MessageUtil messageUtil = new MessageUtil(messageSource);
        return messageUtil;
    }

}
