package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHApprovalDto;
import org.cyfwms.familyhealing.service.FHApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j(topic = "ReferralController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FHApprovalController {
    @Autowired
    private FHApprovalService fhApprovalService;

    @GetMapping(value = "/readApproval/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read FHApproval")
    @ResponseStatus(HttpStatus.OK)
    public FHApprovalDto readApproval(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("ReadFHApproval fhFileDetailsId :" + fhFileDetailsId);
        return fhApprovalService.readApproval(fhFileDetailsId);
    }

    @PutMapping(value = "/saveApproval", produces = "application/json")
    @ApiOperation("Save or Update FHApproval")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHApprovalDto> saveApproval(@RequestBody FHApprovalDto FHApprovalDto) {
        FHApprovalDto fHApprovalDto = fhApprovalService.saveApproval(FHApprovalDto);
        log.info("SaveFHApproval :" + FHApprovalDto);
        return new ResponseEntity<>(fHApprovalDto, HttpStatus.CREATED);
    }
}
