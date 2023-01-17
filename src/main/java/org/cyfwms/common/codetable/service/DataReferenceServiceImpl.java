package org.cyfwms.common.codetable.service;

import lombok.AllArgsConstructor;
import org.cyfwms.common.codetable.constants.ResponseDataType;
import org.cyfwms.common.codetable.dto.DataResponseDto;
import org.cyfwms.common.codetable.helper.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataReferenceServiceImpl implements DataReferenceService{

    private DataReferenceParticipant dataReferenceParticipant;
    private DataReferenceInitialcontact dataReferenceInitilcontact;
    private DataRefCluturalProgAndAct   dataRefCluturalProgAndAct;
    private DataRefCaregivers  dataRefCaregivers;
    private DataRefAppointment dataRefAppointment;
    private DataRefAdditionalStaff dataRefAdditionalStaff;
    private DataRefFamilyHealing dataRefFamilyHealing;

    private DataRefStaff dataRefStaff;

    public DataResponseDto getAllGenderTypes() {
        return DataResponseDto.builder()
                .type(ResponseDataType.gender.name())
                .valuesMap(dataReferenceParticipant.getGender())
                .build();
    }


    public DataResponseDto getAllMaritalStatusValue() {
        return
                DataResponseDto.builder()
                .type(ResponseDataType.maritalStatus.name())
                .valuesMap(dataReferenceParticipant.getMaritalStatus())
                .build();
}
public DataResponseDto getAllProvinceValue() {
        return
                DataResponseDto.builder()
                .type(ResponseDataType.provin.name())
                .valuesMap(dataReferenceParticipant.getProvince())
                .build();
}

    public DataResponseDto getAllRoleValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.role.name())
                        .valuesMap(dataReferenceParticipant.getRole())
                        .build();
    }


    public DataResponseDto getAllEducationValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.education.name())
                        .valuesMap(dataReferenceParticipant.getEducation())
                        .build();
    }

    public DataResponseDto getAllEmployeeValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.typeOfEmployee.name())
                        .valuesMap(dataReferenceParticipant.getTypeOfEmployee())
                        .build();
    }

    @Override
    public DataResponseDto getAllInitialContactStatusValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.initialContactStatus.name())
                        .valuesMap(dataReferenceInitilcontact.getInitialContactStatus())
                        .build();
    }

    @Override
    public DataResponseDto getAllInitialContactReferralValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.initialContactReferral.name())
                        .valuesMap(dataReferenceInitilcontact.getInitialContactReferral())
                        .build();
    }

    @Override
    public DataResponseDto getAllPresentConcernsValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.presentConcerns.name())
                        .valuesMap(dataReferenceInitilcontact.getPresentConcerns())
                        .build();
    }

    @Override
    public DataResponseDto getAllMentalHealthOrSubstanceAbuseValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.mentalHealthOrSubstanceAbuse.name())
                        .valuesMap(dataReferenceInitilcontact.getMentalHealthOrSubstanceAbuse())
                        .build();
    }

    @Override
    public DataResponseDto getAllTypeOfPatientValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.typeOfPatient.name())
                        .valuesMap(dataReferenceInitilcontact.getTypeOfPatient())
                        .build();
    }

    @Override
    public DataResponseDto getAllRiskValue() {
        return
                DataResponseDto.builder()
                        .type(ResponseDataType.risk.name())
                        .valuesMap(dataReferenceInitilcontact.getRisk())
                        .build();
    }

    @Override
    public DataResponseDto getAllContactMethodValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.contactMethod.name())
                .valuesMap(dataReferenceInitilcontact.getContactMethod())
                .build();
    }

    @Override
    public DataResponseDto getAllCulturalTypeValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.cluturalType.name())
                .valuesMap(dataRefCluturalProgAndAct.getCulturalType())
                .build();
    }

    @Override
    public DataResponseDto getAllCulturalStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.culturalStatus.name())
                .valuesMap(dataRefCluturalProgAndAct.getCulturalStatus())
                .build();
    }
    @Override
    public DataResponseDto getAllAppointmentStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.appointmentStatus.name())
                .valuesMap(dataRefAppointment.getAppointmentStatus())
                .build();
    }

    @Override
    public DataResponseDto getAllFrequencyValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.frequency.name())
                .valuesMap(dataRefAppointment.getFrequency())
                .build();
    }

    @Override
    public DataResponseDto getAllCaregiversStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.caregiversstatus.name())
                .valuesMap(dataRefCaregivers.getCaregiverstatus())
                .build();
    }

    @Override
    public DataResponseDto getAllCaregiversTypeValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.caregiverstype.name())
                .valuesMap(dataRefCaregivers.getCaregivertype())
                .build();
    }

    @Override
    public DataResponseDto getAllCaregiversBackgroundStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.caregiverbackgroundstatus.name())
                .valuesMap(dataRefCaregivers.getCaregiverbackgroundstatus())
                .build();
    }

    @Override
    public DataResponseDto getAllReminderStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.reminderstatus.name())
                .valuesMap(dataRefAppointment.getReminderstatus())
                .build();
    }

    @Override
    public DataResponseDto getAllStaffStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.staffStatus.name())
                .valuesMap(dataRefStaff.getStaffStatus())
                .build();
    }

    @Override
    public DataResponseDto getAllBackgroundStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.backgroundstatus.name())
                .valuesMap(dataRefAdditionalStaff.getBackgroundstatus())
                .build();
    }

    @Override
    public DataResponseDto getAllGoalsObjectiveStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.goalsobjectivestatus.name())
                .valuesMap(dataRefAdditionalStaff.getGoalsobjectiveStatus())
                .build();
    }

    @Override
    public DataResponseDto getAllTrainingStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.trainingstatus.name())
                .valuesMap(dataRefAdditionalStaff.getTrainingStatus())
                .build();
    }

    @Override
    public DataResponseDto getAllReferralStatusValue() {
        return  DataResponseDto.builder()
                .type(ResponseDataType.referralstatus.name())
                .valuesMap(dataRefFamilyHealing.getReferralstatus())
                .build();
    }

    @Override
    public DataResponseDto getAllReferralReasonsValue() {
        return DataResponseDto.builder()
                .type(ResponseDataType.referralreasons.name())
                .valuesMap(dataRefFamilyHealing.getReferralreasons())
                .build();
    }

    @Override
    public DataResponseDto getAllConsentTypeValue() {
        return DataResponseDto.builder()
                .type(ResponseDataType.consenttype.name())
                .valuesMap(dataRefFamilyHealing.getConsenttype())
                .build();
    }
}
