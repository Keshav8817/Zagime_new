package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHReferralDto;
import org.cyfwms.familyhealing.service.FHReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "ReferralController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FHReferralController {
    @Autowired
    private FHReferralService fhReferralService;

    @GetMapping(value = "/readReferral/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read FHReferral")
    @ResponseStatus(HttpStatus.OK)
    public FHReferralDto readReferral(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("ReadFHReferral fhFileDetailsId :" + fhFileDetailsId);
        return fhReferralService.readReferral(fhFileDetailsId);
    }

    @PutMapping(value = "/saveReferral", produces = "application/json")
    @ApiOperation("Save or Update FHReferral")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHReferralDto> saveReferral(@RequestBody FHReferralDto FHReferralDto) {
        FHReferralDto fHReferralDto = fhReferralService.saveReferral(FHReferralDto);
        log.info("SaveFHReferral :" + FHReferralDto);
        return new ResponseEntity<>(fHReferralDto, HttpStatus.CREATED);
    }
}
