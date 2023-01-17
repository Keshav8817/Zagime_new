package org.cyfwms.initialcontact.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.*;
import org.cyfwms.initialcontact.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/initialcontactservice")
@CrossOrigin("*")
@Slf4j
public class ZAInitialContactController {
    @Autowired
    private ICFileDetailsService iCFileDetailsService;
    @Autowired
    private ICIncidentReportService iCIncidentReportService;
    @Autowired
    private ICPatientCareInfoService iCPatientCareInfoService;
    @Autowired
    private ICPresentConcernsService iCPresentConcernsService;
    @Autowired
    private ICReferralInfoService iCReferralInfoService;
    @Autowired
    private ICContactNotesService iCContactNotesService;
    @Autowired
    private ICContactNotesSearchService iCContactNotesSearchService;
    @Autowired
    private ICParticipantService icParticipantService;

    @Autowired
    private ICParticipantSearchService icParticipantSearchService;
    private ICReminderService icReminderService;
    @Autowired
    private ICAppointmentService icAppointmentService;
    @Autowired
    private ICAppointmentSearchService icAppointmentSearchService;

    @Autowired
    private ICSearchReminderService iCSearchReminderService;

    @GetMapping(value = "/readAllFileDetails/{filedetailsid}", produces = "application/json")
    @ApiOperation("Read Identity")
    @ResponseStatus(HttpStatus.OK)
    public ICFileDetailsDto readAllFileDetails(@PathVariable("filedetailsid") Long fileDetailsID) {
        log.info("ReadAllFileDetails " + "fileDetailsID :" + fileDetailsID);
        return iCFileDetailsService.readAllFileDetails(fileDetailsID);
    }

    @PutMapping(value = "/saveAllFileDetails", produces = "application/json")
    @ApiOperation("Save or Update Identity")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ICFileDetailsDto> saveAllFileDetails(@RequestBody ICFileDetailsDto iCFileDetailsDto) {
        ICFileDetailsDto icFileDetailsDto = iCFileDetailsService.saveAllFileDetails(iCFileDetailsDto);
        log.info("SaveAllFileDetails " + iCFileDetailsDto);
        return new ResponseEntity<>(icFileDetailsDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readAllIncidentReports/{filedetailsid}", produces = "application/json")
    @ApiOperation("Read IncidentReports")
    @ResponseStatus(HttpStatus.OK)
    public ICIncidentReportDto readAllIncidentReports(@PathVariable("filedetailsid") Long fileDetailsID) {
        log.info("ReadAllIncidentReports " + "FileDetailsID :" + fileDetailsID);
        return iCIncidentReportService.readAllIncidentReports(fileDetailsID);
    }

    @PutMapping(value = "/saveAllIncidentReports", produces = "application/json")
    @ApiOperation("Save or Update IncidentReports")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ICIncidentReportDto> saveAllIncidentReports(
            @RequestBody ICIncidentReportDto iCIncidentReportDto) {
        iCIncidentReportDto = iCIncidentReportService.saveAllIncidentReports(iCIncidentReportDto);
        log.info("SaveAllIncidentReports " + iCIncidentReportDto);
        return new ResponseEntity<>(iCIncidentReportDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readAllPatientCareInfo/{filedetailsid}", produces = "application/json")
    @ApiOperation("Read PatientCareInfo")
    @ResponseStatus(HttpStatus.OK)
    public ICPatientCareInfoDto readAllPatientCareInfo(@PathVariable("filedetailsid") Long fileDetailsID) {
        log.info("ReadAllPatientCareInfo " + "FileDetailsID :" + fileDetailsID);
        return iCPatientCareInfoService.readAllPatientCareInfo(fileDetailsID);
    }

    @PutMapping(value = "/saveAllPatientCareInfo", produces = "application/json")
    @ApiOperation("Save or Update PatientCareInfo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ICPatientCareInfoDto> saveAllPatientCareInfo(
            @RequestBody ICPatientCareInfoDto iCPatientCareInfoDto) {
        iCPatientCareInfoDto = iCPatientCareInfoService.saveAllPatientCareInfo(iCPatientCareInfoDto);
        log.info("SaveAllPatientCareInfo " + iCPatientCareInfoDto);
        return new ResponseEntity<>(iCPatientCareInfoDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readAllPresentConcerns/{filedetailsid}", produces = "application/json")
    @ApiOperation("Read PresentConcerns")
    @ResponseStatus(HttpStatus.OK)
    public ICPresentConcernsDto readPresentConcerns(@PathVariable("filedetailsid") Long fileDetailsID) {
        log.info("ReadAllPresentConcerns " + "FileDetailsID :" + fileDetailsID);
        return iCPresentConcernsService.readPresentConcerns(fileDetailsID);
    }

    @PutMapping(value = "/saveAllPresentConcerns", produces = "application/json")
    @ApiOperation("Save or Update PresentConcerns")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ICPresentConcernsDto> savePresentConcerns(
            @RequestBody ICPresentConcernsDto iCPresentConcernsDto) {
        iCPresentConcernsDto = iCPresentConcernsService.savePresentConcerns(iCPresentConcernsDto);
        log.info("SaveAllPresentConcerns " + iCPresentConcernsDto);
        return new ResponseEntity<>(iCPresentConcernsDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readAllReferralInfo/{filedetailsid}", produces = "application/json")
    @ApiOperation("Read ReferralInfo")
    @ResponseStatus(HttpStatus.OK)
    public ICReferralInfoDto readAllReferralInfo(@PathVariable("filedetailsid") Long fileDetailsID) {
        log.info("ReadAllReferralInfo " + "FileDetailsID :" + fileDetailsID);
        return iCReferralInfoService.readAllReferralInfo(fileDetailsID);
    }

    @PutMapping(value = "/saveAllReferralInfo", produces = "application/json")
    @ApiOperation("Save or Update ReferralInfo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ICReferralInfoDto> saveAllReferralInfo(@RequestBody ICReferralInfoDto iCReferralInfoDto) {
        iCReferralInfoDto = iCReferralInfoService.saveAllReferralInfo(iCReferralInfoDto);
        log.info("SaveAllReferralInfo " + iCReferralInfoDto);
        return new ResponseEntity<>(iCReferralInfoDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readAllContactNotes/{contactnotesid}", produces = "application/json")
    @ApiOperation("Read ContactNotes")
    @ResponseStatus(HttpStatus.OK)
    public ICContactNotesDto readContactNotes(@PathVariable("contactnotesid") Long contactNotesId) {
        log.info("ReadAllContactNotes " + "ContactNotesId :" + contactNotesId);
        return iCContactNotesService.readContactNotes(contactNotesId);
    }

    @PutMapping(value = "/saveAllContactNotes", produces = "application/json")
    @ApiOperation("Save or Update ContactNotes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ICContactNotesDto> saveContactNotes(@RequestBody ICContactNotesDto iCContactNotesDto) {
        iCContactNotesDto = iCContactNotesService.saveContactNotes(iCContactNotesDto);
        log.info("SaveContactNotes " + iCContactNotesDto);
        return new ResponseEntity<>(iCContactNotesDto, HttpStatus.CREATED);
    }

    @GetMapping(value = { "/searchContactNotes/{filedetailsid}/{data}" }, produces = "application/json")
    @ApiOperation("Search InitialContact")
    @ResponseStatus(HttpStatus.OK)
    public List<ICContactNotesSearchResultsDto> searchICContactNotes(@PathVariable Map<String, String> var) {
        ICContactNotesSearchCriteriaDto iCContactNotesSearchCriteriaDto = new ICContactNotesSearchCriteriaDto();
        iCContactNotesSearchCriteriaDto.setFileDetailsId(("null".equals(var.get("filedetailsid"))
                || var.get("filedetailsid") == null) ? null : Long.parseLong(var.get("filedetailsid")));
        iCContactNotesSearchCriteriaDto.setData(
                ("null".equals(var.get("data"))
                        || var.get("data") == null) ? null : var.get("data"));
        log.info("SearchICContactNotes " + iCContactNotesSearchCriteriaDto);
        log.info("Search InitialContact :"+iCContactNotesSearchCriteriaDto);
        return iCContactNotesSearchService.search(iCContactNotesSearchCriteriaDto);
    }

    @DeleteMapping("/removeContactNotes/{contactNotesId}")
    @ApiOperation("Remove Contact Notes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeContactNotes(@PathVariable("contactNotesId") Long contactNotesId) {
        iCContactNotesService.removeContactNotes(contactNotesId);
        log.info("RemoveContactNotes " + "ContactNotesId :" + contactNotesId);
        return new ResponseEntity("Operation Successful", HttpStatus.OK);
    }

    @GetMapping(value = "/readICParticipant/{icparticipantid}", produces = "application/json")
    @ApiOperation("Read ICParticipant")
    @ResponseStatus(HttpStatus.OK)
    public ICParticipantDto readICParticipant(@PathVariable("icparticipantid") Long icParticipantId) {
        log.info("Read ICParticipant By ICParticipantId :"+icParticipantId);
        return icParticipantService.readICParticipant(icParticipantId);
    }


    @PutMapping(value = "/saveICParticipant", produces = "application/json")
    @ApiOperation("Save or Update ICParticipant")
    @ResponseStatus(HttpStatus.OK)
    public ICParticipantDto saveICParticipant(@RequestBody ICParticipantDto iCParticipantDto) {
        log.info("SaveICParticipant " + iCParticipantDto);
        return icParticipantService.saveICParticipant(iCParticipantDto);
    }

    @DeleteMapping("/removeICParticipant/{icparticipantid}")
    @ApiOperation("Remove ICParticipant")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeICParticipant(@PathVariable("icparticipantid") Long icParticipantId) {
        icParticipantService.removeICParticipant(icParticipantId);
        log.info("RemoveICParticipant " + "IcParticipantId :" + icParticipantId);
        return new ResponseEntity("Operation Successful", HttpStatus.OK);
    }

    @PutMapping(value = "/saveICReminder", produces = "application/json")
    @ApiOperation("save or update ICReminder")
    @ResponseStatus(HttpStatus.OK)
    public List<ICReminderDto> saveICReminder(@RequestBody ICReminderDto icReminderDto) {
        log.info("SaveICReminder :" + icReminderDto);
        return icReminderService.saveICReminder(icReminderDto);
    }

    @GetMapping(value = "/readICReminder/{fileDetailsId}", produces = "application/json")
    @ApiOperation("Read ICReminder By fileDetailsId")
    @ResponseStatus(HttpStatus.OK)
    public ICReminderDto readICReminder(@PathVariable("fileDetailsId") Long fileDetailsId) {
        log.info("ReadICReminder " + "FileDetailsId :" + fileDetailsId);
        return icReminderService.readICReminder(fileDetailsId);
    }

    @DeleteMapping(value = "/removeICReminder/{icReminderId}", produces = "application/json")
    @ApiOperation("Remove ICReminder by icReminderId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeICReminder(@PathVariable("icReminderId") Long icReminderId) {
        icReminderService.removeICReminder(icReminderId);
        log.info("RemoveICReminder " + "IcReminderId :" + icReminderId);
        return new ResponseEntity("Operation Successful", HttpStatus.OK);
    }

    @GetMapping(value = {"/searchICReminder/{fileDetailsId}/{data}"}, produces = "application/json")
    @ApiOperation("Search CareGiver Reminder")
    @ResponseStatus(HttpStatus.OK)
    public List<ICSearchReminderResultDto> searchICReminder(@PathVariable Map<String, String> var) {
        ICSearchReminderDto iCSearchReminderDto = new ICSearchReminderDto();
        iCSearchReminderDto.setFileDetailsId(("null".equals(var.get("fileDetailsId"))
                || var.get("fileDetailsId") == null) ? null : Long.parseLong(var.get("fileDetailsId")));
        iCSearchReminderDto.setData(
                ("null".equals(var.get("data"))
                        || var.get("data") == null) ? null : var.get("data"));
        log.info("Search CareGiver Reminder :"+iCSearchReminderDto);
        return iCSearchReminderService.searchICReminder(iCSearchReminderDto);
    }

    @GetMapping(value = { "/participantICSearch/{filedetailsid}/{data}" }, produces = "application/json")
    @ApiOperation("Search ICParticipant")
    @ResponseStatus(HttpStatus.OK)
    public List<ICParticipantSearchResultDto> searchICParticipant(@PathVariable Map<String, String> var) {
        ICParticipantSearchCriteriaDto iCParticipantSearchCriteriaDto = new ICParticipantSearchCriteriaDto();
        iCParticipantSearchCriteriaDto.setFileDetailsId(("null".equals(var.get("filedetailsid"))
                || var.get("filedetailsid") == null) ? null : Long.parseLong(var.get("filedetailsid")));
        iCParticipantSearchCriteriaDto.setData(
                ("null".equals(var.get("data"))
                        || var.get("data") == null) ? null : var.get("data"));
        log.info("Search ICParticipant :"+iCParticipantSearchCriteriaDto);
        return icParticipantSearchService.searchICParticipant(iCParticipantSearchCriteriaDto);
    }
    @PutMapping(value = "/saveICAppointment", produces = "application/json")
    @ApiOperation("Save or Update InitialContact Appointment Informtion")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ICAppointmentDto> saveParticipantAppointment(@RequestBody ICAppointmentDto  icAppointmentDto) {
        log.info("Save or Update InitialContact Appointment Informtion :"+icAppointmentDto);
        return icAppointmentService.saveICAppointment(icAppointmentDto);

    }
    @DeleteMapping("/deleteICAppointment/{ICAppointmentId}")
    @ApiOperation("Remove Counselor CFS Workers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeParticipantAppointment(@PathVariable("ICAppointmentId") Long ICAppointmentId) {
        log.info("Remove Counselor CFS Workers "+ICAppointmentId);
        icAppointmentService.removeICAppointment(ICAppointmentId);
    }
    @GetMapping(value = "/readOneAppointment/{ICAppointmentId}", produces = "application/json")
    @ApiOperation("Read ContactNotes")
    @ResponseStatus(HttpStatus.OK)
    public ICAppointmentDto readOneAppointment(@PathVariable("ICAppointmentId") Long ICAppointmentId) {
        log.info("Read ContactNotes By ICAppointmentId :"+ICAppointmentId);
        return icAppointmentService.readOneAppointment(ICAppointmentId);
    }
    @GetMapping(value = {"/searchICAppointment/{fileDetailsId}/{data}"},produces = "application/json")
    @ApiOperation("Search Participant")
    @ResponseStatus(HttpStatus.OK)
    public List<ICAppointmentSearchResultDto> searchICAppointment(@PathVariable Map<String, String> var)
    {
        ICAppointmentSearchDto icAppointmentSearchDto = new ICAppointmentSearchDto();
        icAppointmentSearchDto.setFileDetailsId(("null".equals(var.get("fileDetailsId"))
                ||var.get("fileDetailsId")==null) ? null:Long.parseLong(var.get("fileDetailsId")));
        icAppointmentSearchDto.setData(
                ("null".equals(var.get("data"))
                        || var.get("data") == null) ?null:var.get("data"));
        log.info("Search Participant :"+icAppointmentSearchDto);
        return icAppointmentSearchService.searchICAppointment(icAppointmentSearchDto);
    }

}
