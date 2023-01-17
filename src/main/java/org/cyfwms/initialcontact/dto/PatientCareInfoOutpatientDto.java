package org.cyfwms.initialcontact.dto;
import lombok.Getter;
import lombok.Setter;
public class PatientCareInfoOutpatientDto {
    @Getter
    @Setter
    private Long outpatientId;

    @Getter
    @Setter
    private String therapyOrCounseling;

    @Getter @Setter
    private String therapyTimePeriod;

    @Getter @Setter
    private String therapyLocation;

    @Getter @Setter
    private String reasonForTherapy;

    @Getter @Setter
    private String selfHelpGroup;

    @Getter @Setter
    private String selfHelpGroupPeriod;

    @Getter @Setter
    private String selfHelpGroupLocation;

    @Getter @Setter
    private Long patientCareInfoId;
}
