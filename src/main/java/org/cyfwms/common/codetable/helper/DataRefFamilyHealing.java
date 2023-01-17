package org.cyfwms.common.codetable.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "familyhealing")
@AllArgsConstructor
@Data
public class DataRefFamilyHealing {
    private Map<String, Map<String,String>> referralstatus;
    private Map<String, Map<String,String>> referralreasons;
    private Map<String, Map<String,String>> consenttype;

}
