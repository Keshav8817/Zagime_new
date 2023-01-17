package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHHistoryDto;
import org.cyfwms.familyhealing.dto.FHReferralDto;
import org.cyfwms.familyhealing.service.FHHistoryService;
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
public class FHHistoryController {
    @Autowired
    private FHHistoryService fHHistoryService;

    @GetMapping(value = "/readHistory/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read FHHistory")
    @ResponseStatus(HttpStatus.OK)
    public FHHistoryDto readHistory(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("ReadFHHistory fhFileDetailsId :" + fhFileDetailsId);
        return fHHistoryService.readHistory(fhFileDetailsId);
    }

    @PutMapping(value = "/saveHistory", produces = "application/json")
    @ApiOperation("Save or Update FHHistory")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHHistoryDto> saveHistory(@RequestBody FHHistoryDto FHHistoryDto) {
        FHHistoryDto fHHistoryDto = fHHistoryService.saveHistory(FHHistoryDto);
        log.info("SaveFHHistory :" + FHHistoryDto);
        return new ResponseEntity<>(fHHistoryDto, HttpStatus.CREATED);
    }
}
