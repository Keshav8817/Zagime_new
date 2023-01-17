package org.cyfwms.staff.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class StaffMedicalAndEmergencyDto {
    @Getter
    @Setter
    private Long staffMedicalAndEmergencyId=0l;

    @Getter
    @Setter
    private String notes;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String relationship;

    @Getter
    @Setter
    private String AlternativeContactMethod;

    @Getter
    @Setter
    private Long staffId;
}
