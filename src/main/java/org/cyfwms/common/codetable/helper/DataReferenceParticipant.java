package org.cyfwms.common.codetable.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "participant")
//@TestPropertySource(locations="classpath:application-participant.yml")
@AllArgsConstructor
@Data
public class DataReferenceParticipant {
    private Map<String,Map<String,String>> gender;
    private Map<String,Map<String,String>> maritalStatus;
    private Map<String,Map<String,String>> Province;
    private Map<String,Map<String,String>> role;
    private Map<String,Map<String,String>> education;
    private Map<String,Map<String,String>> typeOfEmployee;




}
