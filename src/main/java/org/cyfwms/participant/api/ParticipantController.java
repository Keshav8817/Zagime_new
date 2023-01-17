package org.cyfwms.participant.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.*;
import org.cyfwms.participant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j(topic = "ParticipantController")
@AllArgsConstructor
@RequestMapping("/v1/participantservice")
@CrossOrigin("*")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ParticipantOtherInformationService participantOtherInformationService;

    @Autowired
    private CounselorCFSWorkerService counselorCFSWorkerService;

    @Autowired
    private ParticipantContactService participantContactService;

    @Autowired
    private HouseholdMemberService householdMemberService;

    @Autowired
    private CriminalHistoryService criminalHistoryService;

    @Autowired
    private EducationAndEmploymentService educationAndEmploymentService;

    @Autowired
    private FamilyPhysicianService familyPhysicianService;

    @Autowired
    private ParticipantSearchService participantSearchService;

    @Autowired
    private ParticipantAppointmentService participantAppointmentService;
    @Autowired
    private ParticipantCommonDataService participantCommonDataService;
    @Autowired
    private ParticipantAppointmentSearchService participantContactNotesSearchService;
    @Autowired
    private ParticipantReminderService participantReminderService;

    @Autowired
    private ParticipantReminderSearchService participantReminderSearchService;

    @GetMapping(value = "/readParticipantIdentity/{participantid}", produces = "application/json")
    @ApiOperation("Read Identity")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantIdentityDto readParticipantIdentity(@PathVariable("participantid") Long participantId)
            throws Exception {
        log.info("ReadParticipantIdentity ParticipantId :" + participantId);
        return participantService.readParticipantIdentity(participantId);
    }

    @PutMapping(value = "/saveParticipantIdentity", produces = "application/json")
    @ApiOperation("Save or Update Identity")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ParticipantIdentityDto> saveParticipantIdentity(@RequestParam Map<String, String> params,
            @RequestParam(value = "image", required = false) MultipartFile multipartFile) throws IOException {
        ParticipantIdentityDto participantIdentityDto = new ParticipantIdentityDto();
        mapParticipantFormData(participantIdentityDto, params);
        participantIdentityDto = participantService.saveParticipantIdentity(participantIdentityDto, multipartFile);
        log.info("SaveParticipantIdentity :" + participantIdentityDto);
        return ResponseEntity.ok(participantIdentityDto);
    }

    @DeleteMapping("/removeParticipant/{referenceId}")
    @ApiOperation("Remove Participant")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeParticipant(@PathVariable("referenceId") Long referenceId) {
        log.info("RemoveParticipant By ReferenceId :" + referenceId);
        participantService.removeParticipant(referenceId);
    }

    @GetMapping(value = "/readParticipantContact/{participantid}", produces = "application/json")
    @ApiOperation("Read Contact")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantContactDto readParticipantContact(@PathVariable("participantid") Long participantId) {
        log.info("ReadParticipantContact participantId :" + participantId);
        return participantContactService.readParticipantContact(participantId);
    }

    @PutMapping(value = "/saveParticipantContact", produces = "application/json")
    @ApiOperation("Save or Update Contact")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ParticipantContactDto> saveParticipantContact(
            @RequestBody ParticipantContactDto ParticipantContactDto) {
        ParticipantContactDto participantContactDto = participantContactService
                .saveParticipantContact(ParticipantContactDto);
        log.info("SaveParticipantContact :" + participantContactDto);
        return new ResponseEntity<>(participantContactDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllHouseholdMembers/{participantid}", produces = "application/json")
    @ApiOperation("Read All Household Members")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseholdMemberDto> getAllHouseholdMembers(@PathVariable("participantid") Long participantId) {
        log.info("GetAllHouseHoldMembers ParticipantId :" + participantId);
        return householdMemberService.getAllHouseholdMembers(participantId);
    }

    @PutMapping(value = "/saveAllHouseholdMembers", produces = "application/json")
    @ApiOperation("Save All Household Members")
    @ResponseStatus(HttpStatus.CREATED)
    public List<HouseholdMemberDto> saveAllHouseholdMembers(
            @RequestBody List<HouseholdMemberDto> HouseholdMemberDtoList) {
        log.info("SaveAllHouseHoldMembers :" + HouseholdMemberDtoList);
        return householdMemberService.saveAllHouseholdMembers(HouseholdMemberDtoList);
    }

    @DeleteMapping("/removeAddMoreHouseholdMember/{householdMemberId}")
    @ApiOperation("Remove Household Members")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<HouseholdMemberDto> removeHouseholdMembers(@PathVariable("householdMemberId") Long householdMemberId) {
        householdMemberService.removeHouseholdMembers(householdMemberId);
        log.info("RemoveAddMoreHouseHoldMember HouseholdMemberId :" + householdMemberId);
        return householdMemberService.removeHouseholdMembers(householdMemberId);

    }

    @GetMapping(value = "/readCriminalHistory/{participantid}", produces = "application/json")
    @ApiOperation("Read Criminal History")
    @ResponseStatus(HttpStatus.OK)
    public CriminalHistoryDto readCriminalHistory(@PathVariable("participantid") Long participantId) {
        log.info("ReadCriminalHistory ParticipantId :" + participantId);
        return criminalHistoryService.readCriminalHistory(participantId);
    }


    @PutMapping(value = "/saveCriminalHistory", produces = "application/json")
    @ApiOperation("Save or Update Criminal History")
    @ResponseStatus(HttpStatus.CREATED)
    public CriminalHistoryDto saveCriminalHistory(@RequestBody CriminalHistoryDto criminalHistoryDto) {
        log.info("SaveCriminalHistory :" + criminalHistoryDto);
        return criminalHistoryService.saveCriminalHistory(criminalHistoryDto);
    }

    @DeleteMapping("/removeAddMoreCriminalHistory/{criminalhistoryrecordid}")
    @ApiOperation("Remove Criminal History Record")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CriminalHistoryDto criminalHistoryRecord(@PathVariable("criminalhistoryrecordid") Long criminalHistoryRecordId) {
        log.info("RemoveCriminalHistoryRecords CriminalHistoryRecordId :" + criminalHistoryRecordId);
        criminalHistoryService.removeCriminalHistoryRecord(criminalHistoryRecordId);
        return criminalHistoryService.removeCriminalHistoryRecord(criminalHistoryRecordId);
    }

    @GetMapping(value = "/getAllFamilyPhysicians/{participantid}", produces = "application/json")
    @ApiOperation("Read All Family Physicians")
    @ResponseStatus(HttpStatus.OK)
    public List<FamilyPhysicianDto> getAllFamilyPhysicians(@PathVariable("participantid") Long participantId) {
        log.info("GetAllFamilyPhysicians ParticipantId :" + participantId);
        return familyPhysicianService.getAllFamilyPhysicians(participantId);
    }

    @PutMapping(value = "/saveAllFamilyPhysicians", produces = "application/json")
    @ApiOperation("Save All Family Physicians")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FamilyPhysicianDto> saveAllFamilyPhysicians(
            @RequestBody List<FamilyPhysicianDto> FamilyPhysicianDtoList) {
        log.info("SaveAllFamilyPhysicians :" + FamilyPhysicianDtoList);
        return familyPhysicianService.saveAllFamilyPhysicians(FamilyPhysicianDtoList);
    }

    @DeleteMapping("/removeAddMoreFamilyPhysician/{familyPhysicianId}")
    @ApiOperation("Remove Family Physicians")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<FamilyPhysicianDto> removeFamilyPhysician(@PathVariable("familyPhysicianId") Long familyPhysicianId) {
        log.info("RemoveFamilyPhysician FamilyPhysicianId :" + familyPhysicianId);
        return familyPhysicianService.removeFamilyPhysician(familyPhysicianId);
    }

    @GetMapping(value = "/getAllCounselorCFSWorkers/{participantid}", produces = "application/json")
    @ApiOperation("Read All Counselor CFS Workers")
    @ResponseStatus(HttpStatus.OK)
    public List<CounselorCFSWorkersDto> getAllCounselorCFSWorkers(@PathVariable("participantid") Long participantId) {
        log.info("GetAllCounselorCFSWorkers ParticipantId :" + participantId);
        return counselorCFSWorkerService.getAllCounselorCFSWorkers(participantId);
    }


    @PutMapping(value = "/saveAllCounselorCFSWorkers", produces = "application/json")
    @ApiOperation("Save All Counselor CFS Workers")
    @ResponseStatus(HttpStatus.OK)
    public List<CounselorCFSWorkersDto> saveAllCounselorCFSWorkers(
            @RequestBody List<CounselorCFSWorkersDto> FamilyPhysicianDtoList) {
        log.info("SaveAllCounselorCFSWorkers :" + FamilyPhysicianDtoList);
        return counselorCFSWorkerService.saveAllCounselorCFSWorkers(FamilyPhysicianDtoList);
    }

    @DeleteMapping("/removeAddMoreCounselorCFSWorker/{counselorcfsworkerid}")
    @ApiOperation("Remove Counselor CFS Workers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CounselorCFSWorkersDto> removeCounselorCFSWorker(@PathVariable("counselorcfsworkerid") Long counselorCFSWorkerId) {
        log.info("RemoveCounselorCFSWorkers CounselorCFSWorkerId :" + counselorCFSWorkerId);
        return counselorCFSWorkerService.removeCounselorCFSWorker(counselorCFSWorkerId);
    }

    @GetMapping(value = "/readParticipantOtherInformation/{participantid}", produces = "application/json")
    @ApiOperation("Read ParticipantOtherInformation")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantOtherInformationServiceDto readParticipantOtherInformation(
            @PathVariable("participantid") Long participantId) {
        log.info("ReadParticipantOtherInformation ParticipantId :" + participantId);
        return participantOtherInformationService.readParticipantOtherInformation(participantId);
    }

    @PutMapping(value = "/saveParticipantOtherInformation", produces = "application/json")
    @ApiOperation("Save ParticipantOtherInformation")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantOtherInformationServiceDto saveParticipantOtherInformation(
            @RequestBody ParticipantOtherInformationServiceDto participantOtherInformationServiceDto) {
        log.info("SaveParticipantOtherInformation :" + participantOtherInformationServiceDto);
        return participantOtherInformationService
                .saveParticipantOtherInformation(participantOtherInformationServiceDto);
    }

    @GetMapping(value = "/readEmploymentAndEducation/{participantid}", produces = "application/json")
    @ApiOperation("Read Participant Employement and Education")
    @ResponseStatus(HttpStatus.OK)
    public EducationAndEmploymentCompositeDto readEmploymentAndEducation(
            @PathVariable("participantid") Long participantId) {
        log.info("ReadEmploymentAndEducation ParticipantId :" + participantId);
        return educationAndEmploymentService.readEducationAndEmployment(participantId);
    }

    @PutMapping(value = "/saveEmploymentAndEducation", produces = "application/json")
    @ApiOperation("Save Participant Employment And Education")
    @ResponseStatus(HttpStatus.CREATED)
    public EducationAndEmploymentCompositeDto saveEmploymentAndEducation(
            @RequestBody EducationAndEmploymentCompositeDto educationAndEmploymentCompositeDto) {
        log.info("SaveEmploymentAndEducation :" + educationAndEmploymentCompositeDto);
        return educationAndEmploymentService.saveEducationAndEmployment(educationAndEmploymentCompositeDto);
    }

    @GetMapping(value = {
            "/searchParticipants/{referenceId}/{firstname}/{middleName}/{surname}/{dateOfBirth}/{maritalStatus}/{city}/{phoneNumber}" }, produces = "application/json")
    @ApiOperation("Search Participants")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipantSearchResultsDto> searchParticipants(
            @PathVariable Map<String, String> var) {
        ParticipantSearchCriteriaDto participantSearchCriteriaDto = getParticipantSearchCriteriaDto(var);
        log.info("SearchParticipants :" + participantSearchCriteriaDto);
        return participantSearchService.search(participantSearchCriteriaDto);
    }

    private ParticipantSearchCriteriaDto getParticipantSearchCriteriaDto(Map<String, String> var) {
        log.info("Inside GetParticipantSearchCriteriaDto");
        ParticipantSearchCriteriaDto participantSearchCriteriaDto = new ParticipantSearchCriteriaDto();
        LocalDate dateTime = null;
        if (!"null".equals(var.get("dateOfBirth"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateTime = LocalDate.parse(var.get("dateOfBirth"), formatter);
        }
        participantSearchCriteriaDto.setFirstname(
                ("null".equals(var.get("firstname"))
                        || var.get("firstname") == null) ? null : var.get("firstname"));
        participantSearchCriteriaDto.setCity(
                ("null".equals(var.get("city"))
                        || var.get("city") == null) ? null : var.get("city"));
        participantSearchCriteriaDto.setDateOfBirth(dateTime);
        participantSearchCriteriaDto.setMaritalStatus(
                ("null".equals(var.get("maritalStatus"))
                        || var.get("maritalStatus") == null) ? null : var.get("maritalStatus"));
        participantSearchCriteriaDto.setSurname(
                ("null".equals(var.get("surname"))
                        || var.get("surname") == null) ? null : var.get("surname"));
        participantSearchCriteriaDto.setMiddleName(
                ("null".equals(var.get("middleName"))
                        || var.get("middleName") == null) ? null : var.get("middleName"));
        participantSearchCriteriaDto.setPhoneNumber(
                ("null".equals(var.get("phoneNumber"))
                        || var.get("phoneNumber") == null) ? null : var.get("phoneNumber"));

        participantSearchCriteriaDto.setReferenceId(("null".equals(var.get("referenceId"))
                || var.get("referenceId") == null) ? null : Long.parseLong(var.get("referenceId")));
        log.info("Exit GetParticipantSearchCriteriaDto");
        return participantSearchCriteriaDto;
    }

    @GetMapping(value = "/readAllOutputParticipant/{referenceId}", produces = "application/json")
    @ApiOperation("Read All Output Participant")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantCommonDataDto readAllOutPutParticipant(@PathVariable("referenceId") Long referenceId) {
        log.info("ReadAllOutPutParticipant ReferenceId :" + referenceId);
        return participantCommonDataService.readParticipantCommonData(referenceId);
    }

    @GetMapping(value = "/readParticipantReminder/{participantReminderId}", produces = "application/json")
    @ApiOperation("Read Participant Reminder By Id")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantReminderDto readParticipantReminder(@PathVariable("participantReminderId") Long participantReminderId) {
        log.info("ReadParticipantReminder ParticipantReminderId :" + participantReminderId);
        return participantReminderService.readParticipantReminder(participantReminderId);
    }

    @PutMapping(value = "/saveParticipantReminder", produces = "application/json")
    @ApiOperation("Save Participant Reminder")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipantReminderDto> saveParticipantReminder(
            @RequestBody ParticipantReminderDto participantReminderDtoList) {
        log.info("SaveParticipantReminder :" + participantReminderDtoList);
        return participantReminderService.saveParticipantReminder(participantReminderDtoList);
    }

    @GetMapping(value = {"/searchParticipantReminder/{participantId}/{data}"}, produces = "application/json")
    @ApiOperation("Search ParticipantReminder")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipantReminderSearchResultsDto> searchParticipantReminder(@PathVariable Map<String, String> var) {
        ParticipantReminderSearchCriteriaDto participantReminderSearchCriteriaDto = new ParticipantReminderSearchCriteriaDto();


        participantReminderSearchCriteriaDto.setParticipantId(("null".equals(var.get("participantId"))
                || var.get("participantId") == null) ? null : Long.parseLong(var.get("participantId")));
        participantReminderSearchCriteriaDto.setData(
                ("null".equals(var.get("data"))
                        || var.get("data") == null) ? null : var.get("data"));

        log.info("SearchParticipantReminder :" + participantReminderSearchCriteriaDto);
        return participantReminderSearchService.search(participantReminderSearchCriteriaDto);
    }

    @DeleteMapping("/removeParticipantReminder/{participantReminderId}")
    @ApiOperation("Remove Participant Reminder By participantReminderId")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeParticipantReminder(@PathVariable("participantReminderId") Long participantReminderId) {
        log.info("RemoveParticipantReminder ParticipantReminderId :" + participantReminderId);
        participantReminderService.removeParticipantReminder(participantReminderId);
    }

    private void mapParticipantFormData(ParticipantIdentityDto participantIdentityDto,
            Map<String, String> params) {
        log.info("Inside ParticipantFormData");
        long participantId = 0;
        if (params.get("participantId") != null &&
                !params.get("participantId").equals("undefined")
                && params.get("participantId").length() > 0) {
            participantId = Long.parseLong(params.get("participantId"));
        }
        participantIdentityDto.setParticipantId(participantId);
        participantIdentityDto.setFirstname(params.get("firstName"));
        participantIdentityDto.setMiddleName(params.get("middleName"));
        participantIdentityDto.setSurname(params.get("lastName"));
        participantIdentityDto.setDateOfBirth(LocalDate.parse(params.get("dateOfBirth")));
        participantIdentityDto.setGender(params.get("gender"));
        participantIdentityDto.setMaritalStatus(params.get("maritalStatus"));
        participantIdentityDto.setRemoveProfilePicture(new Boolean(params.get("removeProfilePicture")));
        log.info("Exit ParticipantFormData");
    }

    @PutMapping(value = "/saveParticipantAppointment", produces = "application/json")
    @ApiOperation("Save or Update Participant Appointment Informtion")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ParticipantAppointmentDto>  saveParticipantAppointment(@RequestBody ParticipantAppointmentDto participantAppointmentDto) {
        log.info("SaveParticipantAppointment :" + participantAppointmentDto);
        return participantAppointmentService.saveParticipantAppointment(participantAppointmentDto);

    }

    @DeleteMapping("/deleteParticipantAppointment/{ParticipantAppointmentId}")
    @ApiOperation("Remove Counselor CFS Workers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeParticipantAppointment(@PathVariable("ParticipantAppointmentId") Long ParticipantAppointmentId) {
        log.info("DeleteParticipantAppointment " + "ParticipantAppointmentId :" + ParticipantAppointmentId);
        participantAppointmentService.removeParticipantAppointment(ParticipantAppointmentId);
    }

    @GetMapping(value = { "/searchParticipantAppointent/{participantid}/{data}" }, produces = "application/json")
    @ApiOperation("Search Participant")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipantAppointmentSearchResultsDto> searchParticipantAppointment(
            @PathVariable Map<String, String> var) {
        ParticipantAppointmentSearchCriteriaDto participantAppointmentSearchCriteriaDto = new ParticipantAppointmentSearchCriteriaDto();
        participantAppointmentSearchCriteriaDto.setParticipantId(("null".equals(var.get("participantid"))
                || var.get("participantid") == null) ? null : Long.parseLong(var.get("participantid")));
        participantAppointmentSearchCriteriaDto.setData(
                ("null".equals(var.get("data"))
                        || var.get("data") == null) ? null : var.get("data"));
        log.info("SearchContactNotes :" + participantAppointmentSearchCriteriaDto);
        return participantContactNotesSearchService.search(participantAppointmentSearchCriteriaDto);
    }

    @GetMapping(value = "/readOneAppointment/{participantAppointmentId}", produces = "application/json")
    @ApiOperation("Read ContactNotes")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantAppointmentDto readOneAppointment(
            @PathVariable("participantAppointmentId") Long participantAppointmentId) {
        log.info("ReadOneAppointment " + "ParticipantAppointmentId :" + participantAppointmentId);
        return participantAppointmentService.readOneAppointment(participantAppointmentId);
    }
}
