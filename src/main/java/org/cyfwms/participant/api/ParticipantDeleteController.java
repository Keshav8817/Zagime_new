package org.cyfwms.participant.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.service.ParticipantDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "ParticipantDeleteController")
@AllArgsConstructor
@RequestMapping("/v1/participantservice")
@CrossOrigin("*")
public class ParticipantDeleteController {
    @Autowired
    private ParticipantDeleteService participantDeleteService;

    @DeleteMapping("/removeParticipantContact/{participantContactId}")
    @ApiOperation("Remove ParticipantContact")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeParticipantContact(@PathVariable("participantContactId") Long participantContactId) {
        log.info("RemoveParticipantContact By participantContactId :" + participantContactId);
        participantDeleteService.removeParticipantContact(participantContactId);
    }

    @DeleteMapping("/removeHouseHoldMember/{participantId}")
    @ApiOperation("Remove HouseHoldMember")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeHouseholdMember(@PathVariable("participantId") Long participantId) {
        log.info("RemoveHouseHoldMember By ParticipantId :" + participantId);
        participantDeleteService.removeHouseholdMember(participantId);
    }

    @DeleteMapping("/removeEducationAndEmployment/{participantId}")
    @ApiOperation("Remove EducationAndEmployment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeEducationAndEmployment(@PathVariable("participantId") Long participantId) {
        log.info("RemoveEducationAndEmployment By participantId :" + participantId);
        participantDeleteService.removeEducationAndEmployment(participantId);
    }
    @DeleteMapping("/removeCriminalHistory/{criminalHistoryId}")
    @ApiOperation("Remove CriminalHistory")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeCriminalHistory(@PathVariable("criminalHistoryId") Long criminalHistoryId) {
        log.info("RemoveCriminalHistory By criminalHistoryId :" + criminalHistoryId);
        participantDeleteService.removeCriminalHistory(criminalHistoryId);
    }

    @DeleteMapping("/removeFamilyPhysician/{participantId}")
    @ApiOperation("Remove FamilyPhysician")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeFamilyPhysician(@PathVariable("participantId") Long participantId) {
        log.info("RemoveFamilyHealing By ParticipantId :" + participantId);
        participantDeleteService.removeFamilyPhysician(participantId);
    }

    @DeleteMapping("/removeCounselorCFSWorker/{participantId}")
    @ApiOperation("Remove CounselorCFSWorker")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeCounselorCFSWorker(@PathVariable("participantId") Long participantId) {
        log.info("RemoveCounselorCFSWorker By ParticipantId :" + participantId);
        participantDeleteService.removeCounselorCFSWorker(participantId);
    }

    @DeleteMapping("/removeParticipantOtherInformation/{participantOtherInfoId}")
    @ApiOperation("Remove ParticipantOtherInformation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeParticipantOtherInformation(@PathVariable("participantOtherInfoId") Long participantOtherInfoId) {
        log.info("RemoveParticipantOtherInformation By participantOtherInfoId :" + participantOtherInfoId);
        participantDeleteService.removeParticipantOtherInformation(participantOtherInfoId);
    }
}
