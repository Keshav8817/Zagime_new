package org.cyfwms.staff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class StaffContactInformationDto {
    @Getter
    @Setter
    private Long staffContactInformationId=0L;

    @Getter
    @Setter

    private String addressLine1;

    @Getter
    @Setter
    private String addressLine2;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String province;

    @Getter
    @Setter
    private String postalCode;

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    private String cellPhone;

    @Getter
    @Setter
    private String homePhone;

    @Getter
    @Setter
    private Long staffId;
}
