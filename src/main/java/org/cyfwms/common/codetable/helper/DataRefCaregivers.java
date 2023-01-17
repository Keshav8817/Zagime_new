package org.cyfwms.common.codetable.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "caregivers")
@AllArgsConstructor
@Data
public class DataRefCaregivers {
    private Map<String, Map<String,String>> caregivertype;
    private Map<String, Map<String,String>> caregiverstatus;
    public Map<String, Map<String, String>> caregiverbackgroundstatus;
}