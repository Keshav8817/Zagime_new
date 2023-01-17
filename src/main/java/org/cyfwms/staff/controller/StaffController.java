package org.cyfwms.staff.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.staff.dto.*;
import org.cyfwms.staff.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j(topic = "StaffController")
@AllArgsConstructor
@RequestMapping("/v1/staffservice")
@CrossOrigin("*")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffSearchService staffSearchService;

    @Autowired
    private StaffContactInformationService staffContactInformationService;
    @Autowired
    private StaffJobAndBankingService staffJobAndBankingService;
    @Autowired
    private StaffMedicalAndEmergencyService staffMedicalAndEmergencyService;

    @Autowired
    private BackgroundCheckService backgroundCheckService;

    @GetMapping(value = "/readStaff/{staffId}", produces = "application/json")
    @ApiOperation("Read Staff")
    @ResponseStatus(HttpStatus.OK)
    public StaffDto readStaff(@PathVariable("staffId") Long staffId) {
        log.info("ReadStaff staffId :" + staffId);
        return staffService.readStaff(staffId);
    }

    @PutMapping(value = "/saveStaff", produces = "application/json")
    @ApiOperation("Save or Update Staff")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StaffDto> saveStaff(@RequestBody StaffDto StaffDto) {
        StaffDto staffDto = staffService.saveStaff(StaffDto);
        log.info("SaveStaff :" + staffDto);
        return new ResponseEntity<>(staffDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/removeStaff/{staffId}")
    @ApiOperation("Remove Staff")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeStaff(@PathVariable("staffId") Long staffId) {
        log.info("RemoveStaff By staffId :" + staffId);
        staffService.removeStaff(staffId);
    }

    @GetMapping(value = {"/searchStaffs/{firstName}/{middleName}/{lastName}/{workLocation}/{supervisor}/{active}"}, produces = "application/json")
    @ApiOperation("Search Staffs")
    @ResponseStatus(HttpStatus.OK)
    public List<StaffSearchResultsDto> searchStaffs(
            @PathVariable Map<String, String> var) {
        StaffSearchCriteriaDto staffSearchCriteriaDto = getStaffSearchCriteriaDto(var);
        log.info("SearchStaffs :" + staffSearchCriteriaDto);
        return staffSearchService.searchStaff(staffSearchCriteriaDto);
    }

    private StaffSearchCriteriaDto getStaffSearchCriteriaDto(Map<String, String> var) {
        log.info("Inside GetStaffSearchCriteriaDto");
        StaffSearchCriteriaDto staffSearchCriteriaDto = new StaffSearchCriteriaDto();

        staffSearchCriteriaDto.setFirstName(
                ("null".equals(var.get("firstName"))
                        || var.get("firstName") == null) ? null : var.get("firstName"));
        staffSearchCriteriaDto.setMiddleName(
                ("null".equals(var.get("middleName"))
                        || var.get("middleName") == null) ? null : var.get("middleName"));
        staffSearchCriteriaDto.setLastName(
                ("null".equals(var.get("lastName"))
                        || var.get("lastName") == null) ? null : var.get("lastName"));
        staffSearchCriteriaDto.setWorkLocation(
                ("null".equals(var.get("workLocation"))
                        || var.get("workLocation") == null) ? null : var.get("workLocation"));
        staffSearchCriteriaDto.setSupervisor(
                ("null".equals(var.get("supervisor"))
                        || var.get("supervisor") == null) ? null : var.get("supervisor"));

        staffSearchCriteriaDto.setActive(Boolean.parseBoolean(var.get("active")));

        log.info("Exit GetStaffSearchCriteriaDto");
        return staffSearchCriteriaDto;
    }


    @PutMapping(value = "/saveContactInformation", produces = "application/json")
    @ApiOperation("Save or Update StaffContactInformation")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StaffContactInformationDto> saveContactInformation(@RequestBody StaffContactInformationDto ContactInformationDto) {
        StaffContactInformationDto staffContactInformationDto = staffContactInformationService.saveContactInformation(ContactInformationDto);
        log.info("saveStaffContactInformation :" + staffContactInformationDto);
        return new ResponseEntity<>(staffContactInformationDto, HttpStatus.CREATED);
    }


    @GetMapping(value = "/readContactInformation/{staffId}", produces = "application/json")
    @ApiOperation("Read StaffContactInformation")
    @ResponseStatus(HttpStatus.OK)
    public StaffContactInformationDto readStaffContactInformation(@PathVariable("staffId") Long staffId) {
        log.info("ReadStaffContactInformation staffId :" + staffId);
        return staffContactInformationService.readStaffContactInformation(staffId);
    }

    @PutMapping(value = "/saveJobAndBanking", produces = "application/json")
    @ApiOperation("Save or Update StaffJobAndBankingDto")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StaffJobAndBankingDto> saveStaffJobAndBanking(@RequestBody StaffJobAndBankingDto StaffJobAndBankingDto) {
        StaffJobAndBankingDto staffJobAndBankingDto = staffJobAndBankingService.saveStaffJobAndBanking(StaffJobAndBankingDto);
        log.info("saveStaffJobAndBankingDto :" + staffJobAndBankingDto);
        return new ResponseEntity<>(staffJobAndBankingDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readJobAndBanking/{staffId}", produces = "application/json")
    @ApiOperation("Read StaffJobAndBankingDto")
    @ResponseStatus(HttpStatus.OK)
    public StaffJobAndBankingDto readStaffJobAndBanking(@PathVariable("staffId") Long staffId) {
        log.info("StaffJobAndBankingDto staffId :" + staffId);
        return staffJobAndBankingService.readStaffJobAndBanking(staffId);
    }


    @PutMapping(value = "/saveMedicalAndEmergency", produces = "application/json")
    @ApiOperation("Save or Update MedicalAndEmergency")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StaffMedicalAndEmergencyDto> saveStaffMedicalAndEmergency(@RequestBody StaffMedicalAndEmergencyDto medicalAndEmergencyDto) {
        StaffMedicalAndEmergencyDto staffJobAndBankingDto = staffMedicalAndEmergencyService.saveStaffMedicalAndEmergency(medicalAndEmergencyDto);
        log.info("saveMedicalAndEmergency :" + staffJobAndBankingDto);
        return new ResponseEntity<>(staffJobAndBankingDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readMedicalAndEmergency/{staffId}", produces = "application/json")
    @ApiOperation("Read StaffMedicalAndEmergency")
    @ResponseStatus(HttpStatus.OK)
    public StaffMedicalAndEmergencyDto readStaffMedicalAndEmergency(@PathVariable("staffId") Long staffId) {
        log.info("saveMedicalAndEmergency staffId :" + staffId);
        return staffMedicalAndEmergencyService.readStaffMedicalAndEmergency(staffId);
    }

    @PutMapping(value = "/saveBackgroundCheck", produces = "application/json")
    @ApiOperation("Save or Update Staff Background Check")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BackgroundCheckDto> saveBackgroundCheck(@RequestBody BackgroundCheckDto backgroundCheckDto) {
        BackgroundCheckDto backgroundcheckDto = backgroundCheckService.saveBackgroundCheck(backgroundCheckDto);
        log.info("saveBackgroundCheck:" + backgroundCheckDto);
        return new ResponseEntity<>(backgroundcheckDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readBackgroundCheck/{backgroundCheckId}", produces = "application/json")
    @ApiOperation("Read Staff Training")
    @ResponseStatus(HttpStatus.OK)
    public BackgroundCheckDto readBackgroundCheck(@PathVariable("backgroundCheckId") Long backgroundCheckId) {
        log.info("readBackgroundCheck backgroundCheckId :" + backgroundCheckId);
        return backgroundCheckService.readBackgroundCheck(backgroundCheckId);
    }

    @GetMapping(value = "/readAllBackgroundCheck/{staffId}", produces = "application/json")
    @ApiOperation("Read All Staff Training")
    @ResponseStatus(HttpStatus.OK)
    public List<BackgroundCheckDto> readAllBackgroundCheck(@PathVariable("staffId") Long staffId) {
        log.info("ReadAllBackgroundCheck staffId :" + staffId);
        return backgroundCheckService.readAllBackgroundCheck(staffId);
    }

    @DeleteMapping("/removeBackgroundCheck/{backgroundCheckId}")
    @ApiOperation("Remove Staff")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeBackgroundCheck(@PathVariable("backgroundCheckId") Long backgroundCheckId) {
        log.info("RemoveBackgroundCheck By backgroundCheckId :" + backgroundCheckId);
        backgroundCheckService.deleteBackgroundCheck(backgroundCheckId);
    }
}