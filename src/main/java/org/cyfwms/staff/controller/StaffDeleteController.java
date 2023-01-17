package org.cyfwms.staff.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.staff.service.StaffDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "StaffController")
@AllArgsConstructor
@RequestMapping("/v1/staffservice")
@CrossOrigin("*")
public class StaffDeleteController {
    @Autowired
    private StaffDeleteService staffDeleteService;

    @DeleteMapping("/removeContactInformation/{staffContactInformationId}")
    @ApiOperation("Remove ContactInformation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeContactInformation(@PathVariable("staffContactInformationId") Long staffContactInformationId) {
        log.info("RemoveContactInformation By staffContactInformationId :" + staffContactInformationId);
        staffDeleteService.removeContactInformation(staffContactInformationId);
    }
    @DeleteMapping("/removeJobAndBanking/{staffJobAndBankingId}")
    @ApiOperation("Remove JobAndBanking")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeJobAndBanking(@PathVariable("staffJobAndBankingId") Long staffJobAndBankingId) {
        log.info("RemoveJobAndBanking By staffJobAndBankingId :" + staffJobAndBankingId);
        staffDeleteService.removeJobAndBanking(staffJobAndBankingId);
    }

    @DeleteMapping("/removeMedicalAndEmergency/{staffMedicalAndEmergencyId}")
    @ApiOperation("Remove JobAndBanking")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeMedicalEmergency(@PathVariable("staffMedicalAndEmergencyId") Long staffMedicalAndEmergencyId) {
        log.info("Remove MedicalAndEmergency By staffMedicalAndEmergencyId :" + staffMedicalAndEmergencyId);
        staffDeleteService.removeMedicalEmergency(staffMedicalAndEmergencyId);
    }

    @DeleteMapping("/removeInventories/{staffId}")
    @ApiOperation("Remove Inventories")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeInventories(@PathVariable("staffId") Long staffId) {
        log.info("RemoveInventories By staffId :" + staffId);
        staffDeleteService.removeInventories(staffId);
    }
}
