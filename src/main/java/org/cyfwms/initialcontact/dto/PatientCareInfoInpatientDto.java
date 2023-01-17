package org.cyfwms.initialcontact.dto;
import lombok.Getter;
import lombok.Setter;
public class PatientCareInfoInpatientDto {
    @Getter @Setter
    private Long inpatientId;

    @Getter
    @Setter
    private String hospitalizationRecord;

    @Getter @Setter
    private String hospitalizationReasons;

    @Getter @Setter
    private Long patientCareInfoId;
}
