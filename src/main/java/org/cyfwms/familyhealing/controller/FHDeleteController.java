package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.service.FHDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "FamilyHealingDeleteController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealingService")
@CrossOrigin("*")
public class FHDeleteController {
    @Autowired
    FHDeleteService fhDeleteService;

    @DeleteMapping("/removeReferral/{fhReferralId}")
    @ApiOperation("Remove FHReferral")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeReferral(@PathVariable("fhReferralId") Long fhReferralId) {
        log.info("RemoveReferral By fhReferralId :" + fhReferralId);
        fhDeleteService.removeReferral(fhReferralId);
    }

    @DeleteMapping("/removeHistory/{fhHistoryId}")
    @ApiOperation("Remove History")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeHistory(@PathVariable("fhHistoryId") Long fhHistoryId) {
        log.info("RemoveHistory By fhHistoryId :" + fhHistoryId);
        fhDeleteService.removeHistory(fhHistoryId);
    }

    @DeleteMapping("/removeApproval/{fhApprovalId}")
    @ApiOperation("Remove Approval")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeApproval(@PathVariable("fhApprovalId") Long fhApprovalId) {
        log.info("RemoveApproval By fhApprovalId :" + fhApprovalId);
        fhDeleteService.removeApproval(fhApprovalId);
    }
}
