package org.cyfwms.common.codetable.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
@Configuration
@ConfigurationProperties(prefix = "initialcontact")
@AllArgsConstructor
@Data
public class DataReferenceInitialcontact {
    private Map<String, Map<String,String>> initialContactStatus;
    private Map<String,Map<String,String>> initialContactReferral;
    private Map<String,Map<String,String>> presentConcerns;
    private Map<String,Map<String,String>> mentalHealthOrSubstanceAbuse;
    private Map<String,Map<String,String>> typeOfPatient;
    private Map<String,Map<String,String>> risk;
    private Map<String,Map<String,String>> contactMethod;
}
