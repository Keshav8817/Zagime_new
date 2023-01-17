package org.cyfwms.common.codetable.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.codetable.dto.DataResponseDto;
import org.cyfwms.common.codetable.service.DataReferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("/v1/dataservice")
@CrossOrigin("*")
@Slf4j
public class DataReferenceController {

    private DataReferenceService dataReferenceService;

    @GetMapping(value = "/gender", produces = "application/json")
    @ApiOperation("Get All Gender Types")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getGenderValues() {
        log.info("Get All Gender Types");
        return dataReferenceService.getAllGenderTypes();
    }
    @GetMapping(value = "/maritalstatus", produces = "application/json")
    @ApiOperation("Get All Marital Status Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getMaritalStatus() {
        log.info("Get All Marital Status Value");
        return dataReferenceService.getAllMaritalStatusValue();
    }
   @GetMapping(value = "/role", produces = "application/json")
    @ApiOperation("Get All Role Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getRoleValue() {
       log.info("Get All Role Value");
        return dataReferenceService.getAllRoleValue();
    }
    @GetMapping(value = "/province", produces = "application/json")
    @ApiOperation("Get All Province Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getProvince() {
        log.info("Get All Province Value");
        return dataReferenceService.getAllProvinceValue();
    }



    @GetMapping(value = "/education", produces = "application/json")
    @ApiOperation("Get All Education Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getEducationValue() {
        log.info("Get All Education Value");
        return dataReferenceService.getAllEducationValue();
    }

    @GetMapping(value = "/typeofemployee", produces = "application/json")
    @ApiOperation("Get All Employee Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getEmployeeValue() {
        log.info("Get All Employee Value");
        return dataReferenceService.getAllEmployeeValue();
    }

    @GetMapping(value = "/initialContactStatus", produces = "application/json")
    @ApiOperation("Get All Employee Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllInitialContactStatusValue() {
        log.info("Get All Employee Value");
        return dataReferenceService.getAllInitialContactStatusValue();
    }
    @GetMapping(value = "/initialContactReferral", produces = "application/json")
    @ApiOperation("Get All Employee Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllInitialContactReferralValue() {
        log.info("Get All Employee Value");
        return dataReferenceService.getAllInitialContactReferralValue();
    }
    @GetMapping(value = "/presentConcerns", produces = "application/json")
    @ApiOperation("Get All Employee Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllPresentConcernsValue() {
       log.info("Get All Employee Value");
        return dataReferenceService.getAllPresentConcernsValue();
    }
    @GetMapping(value = "/mentalHealthOrSubstanceAbuse", produces = "application/json")
    @ApiOperation("Get All Employee Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllMentalHealthOrSubstanceAbuseValue() {
        log.info("Get All Employee Value");
        return dataReferenceService.getAllMentalHealthOrSubstanceAbuseValue();
    }
    @GetMapping(value = "/typeOfPatient", produces = "application/json")
    @ApiOperation("Get All Employee Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllTypeOfPatientValue() {
        log.info("Get All Employee Value");
        return dataReferenceService.getAllTypeOfPatientValue();
    }
    @GetMapping(value = "/risk", produces = "application/json")
    @ApiOperation("Get All Employee Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllRiskValue() {
        log.info("Get All Employee Value");
        return dataReferenceService.getAllRiskValue();
    }
    @GetMapping(value = "/contactMethod", produces = "application/json")
    @ApiOperation("Get All Contact Method Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllContactMethodValue() {
        log.info("Get All Contact Method Value");
        return dataReferenceService.getAllContactMethodValue();
    }
    @GetMapping(value = "/culturaltype", produces = "application/json")
    @ApiOperation("Get All Contact Method Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllCulturalTypeValue() {
        log.info("Get All Contact Method Value");
        return dataReferenceService.getAllCulturalTypeValue();
    }
    @GetMapping(value = "/culturalstatus", produces = "application/json")
    @ApiOperation("Get All Contact Method Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllCulturalStatusValue() {
        log.info("Get All Contact Method Value");
        return dataReferenceService.getAllCulturalStatusValue();
    }
    @GetMapping(value = "/appoinmentstatus", produces = "application/json")
    @ApiOperation("Get All appointment Status Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllAppointmentStatusValue() {
        log.info("Get All appointment Status Value");
        return dataReferenceService.getAllAppointmentStatusValue();
    }

    @GetMapping(value = "/frequency", produces = "application/json")
    @ApiOperation("Get All frequency Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllFrequencyValue() {
        log.info("Get All frequency Value");
        return dataReferenceService.getAllFrequencyValue();
    }
    @GetMapping(value = "/caregiverstatus", produces = "application/json")
    @ApiOperation("Get All caregivers Status Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllCaregiversStatusValue() {
        log.info("Get All caregivers Status Value");
        return dataReferenceService.getAllCaregiversStatusValue();
    }

    @GetMapping(value = "/caregivertype", produces = "application/json")
    @ApiOperation("Get All caregiver type Value")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllCaregiversTypeValue() {
        log.info("Get All caregiver type Value");
        return dataReferenceService.getAllCaregiversTypeValue();
    }

    @GetMapping(value = "/caregiverbackgroundstatus", produces = "application/json")
    @ApiOperation("Get All caregiver Background  status")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllCaregiversBackgroundStatusValue() {

        return dataReferenceService.getAllCaregiversBackgroundStatusValue();
    }
    @GetMapping(value = "/reminderstatus", produces = "application/json")
    @ApiOperation("Get All reminder  status")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllReminderStatusValue() {

        return dataReferenceService.getAllReminderStatusValue();
    }

    @GetMapping(value = "/staffstatus", produces = "application/json")
    @ApiOperation("Get All Staff status")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllStaffStatusValue() {
        return dataReferenceService.getAllStaffStatusValue();
    }

    @GetMapping(value = "/backgroundstatus", produces = "application/json")
    @ApiOperation("Get All Background status")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllBackgroundStatusValue() {
        return dataReferenceService.getAllBackgroundStatusValue();
    }

    @GetMapping(value = "/goalsobjectivestatus", produces = "application/json")
    @ApiOperation("Get All Goals & Objective status")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllGoalsObjectiveStatusValue() {
        return dataReferenceService.getAllGoalsObjectiveStatusValue();
    }

    @GetMapping(value = "/trainingstatus", produces = "application/json")
    @ApiOperation("Get All Training status")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllTrainingStatusValue() {
        return dataReferenceService.getAllTrainingStatusValue();
    }

    @GetMapping(value = "/referralstatus", produces = "application/json")
    @ApiOperation("Get All Referral status")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllReferralStatusValue() {
        return dataReferenceService.getAllReferralStatusValue();
    }

    @GetMapping(value = "/referralreasons", produces = "application/json")
    @ApiOperation("Get All Referral Reasons")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllReferralReasonsValue() {
        return dataReferenceService.getAllReferralReasonsValue();
    }

    @GetMapping(value = "/consenttype", produces = "application/json")
    @ApiOperation("Get All Consent Type")
    @ResponseStatus(HttpStatus.OK)
    public DataResponseDto getAllConsentTypeValue() {
        return dataReferenceService.getAllConsentTypeValue();
    }

}
