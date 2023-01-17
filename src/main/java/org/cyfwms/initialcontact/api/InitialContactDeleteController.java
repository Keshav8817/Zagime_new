package org.cyfwms.initialcontact.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.service.ICDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "InitialContactDeleteController")
@AllArgsConstructor
@RequestMapping("/v1/initialContactService")
@CrossOrigin("*")
public class InitialContactDeleteController {
    @Autowired
    private ICDeleteService icDeleteService;

    @DeleteMapping("/removeReferralInfo/{referralInfoId}")
    @ApiOperation("Remove ReferralInfo")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeReferralInfo(@PathVariable("referralInfoId") Long referralInfoId) {
        log.info("RemoveReferralInfo By referralInfoId :" + referralInfoId);
        icDeleteService.removeReferralInfo(referralInfoId);
    }

    @DeleteMapping("/removeIncidentReport/{incidentReportId}")
    @ApiOperation("Remove IncidentReport")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeIncidentReport(@PathVariable("incidentReportId") Long incidentReportId) {
        log.info("RemoveIncidentReport By incidentReportId :" + incidentReportId);
        icDeleteService.removeIncidentReport(incidentReportId);
    }

    @DeleteMapping("/removePresentConcerns/{presentConcernsId}")
    @ApiOperation("Remove PresentConcerns")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removePresentConcerns(@PathVariable("presentConcernsId") Long presentConcernsId) {
        log.info("RemovePresentConcerns By presentConcernsId :" + presentConcernsId);
        icDeleteService.removePresentConcerns(presentConcernsId);
    }

    @DeleteMapping("/removePatientCareInfo/{patientCareInfoId}")
    @ApiOperation("Remove PatientCareInfo")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removePatientCareInfo(@PathVariable("patientCareInfoId") Long patientCareInfoId) {
        log.info("RemovePatientCareInfo By patientCareInfoId :" + patientCareInfoId);
        icDeleteService.removePatientCareInfo(patientCareInfoId);
    }
}
