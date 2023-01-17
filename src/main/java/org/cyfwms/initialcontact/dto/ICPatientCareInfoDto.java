package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.cyfwms.initialcontact.entity.PatientCareInfoInpatient;
import org.cyfwms.initialcontact.entity.PatientCareInfoOutpatient;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICPatientCareInfoDto {
    @Getter
    @Setter
    private Long fileDetailsId;

    @Getter
    @Setter
    private Long patientCareInfoId;

    @Getter
    @Setter
    private PatientCareInfoOutpatient outpatient;

    @Getter
    @Setter
    private PatientCareInfoInpatient inpatient;

    @Getter
    @Setter
    private String typeOfPatient;

}
