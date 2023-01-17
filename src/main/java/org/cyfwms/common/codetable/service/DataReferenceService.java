package org.cyfwms.common.codetable.service;

import org.cyfwms.common.codetable.dto.DataResponseDto;

public interface DataReferenceService {

    DataResponseDto getAllGenderTypes();
    DataResponseDto getAllMaritalStatusValue();
    DataResponseDto getAllProvinceValue();
    DataResponseDto getAllRoleValue();
    DataResponseDto getAllEducationValue();
    DataResponseDto getAllEmployeeValue();
    DataResponseDto getAllInitialContactStatusValue();
    DataResponseDto getAllInitialContactReferralValue();
    DataResponseDto getAllPresentConcernsValue();
    DataResponseDto getAllMentalHealthOrSubstanceAbuseValue();
    DataResponseDto getAllTypeOfPatientValue();
    DataResponseDto getAllRiskValue();
    DataResponseDto getAllContactMethodValue();
    DataResponseDto getAllCulturalTypeValue();
    DataResponseDto getAllCulturalStatusValue();
    DataResponseDto getAllFrequencyValue();
    DataResponseDto getAllAppointmentStatusValue();
    DataResponseDto getAllCaregiversStatusValue();
    DataResponseDto getAllCaregiversTypeValue();
    DataResponseDto getAllCaregiversBackgroundStatusValue();
    DataResponseDto getAllReminderStatusValue();
    DataResponseDto getAllStaffStatusValue();
    DataResponseDto getAllBackgroundStatusValue();
    DataResponseDto getAllGoalsObjectiveStatusValue();
    DataResponseDto getAllTrainingStatusValue();
    DataResponseDto getAllReferralStatusValue();
    DataResponseDto getAllReferralReasonsValue();
    DataResponseDto getAllConsentTypeValue();
}