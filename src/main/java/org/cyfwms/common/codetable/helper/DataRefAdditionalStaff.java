package org.cyfwms.common.codetable.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "additionalstaff")
@AllArgsConstructor
@Data
public class DataRefAdditionalStaff {
    private Map<String, Map<String,String>> backgroundstatus;
    private Map<String, Map<String,String>> goalsobjectiveStatus;
    private Map<String, Map<String,String>> trainingStatus;
}
