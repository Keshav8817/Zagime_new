package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHNeedAssessmentDto;
import org.cyfwms.familyhealing.service.FHNeedAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j(topic = "NeedsAssessmentController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FHNeedAssessmentController {
    @Autowired
    private FHNeedAssessmentService fhNeedAssessmentService;

    @PutMapping(value = "/saveNeedAssessment", produces = "application/json")
    @ApiOperation("Save or Update  FHNeedAssessment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHNeedAssessmentDto> saveNeedAssessment(@RequestBody FHNeedAssessmentDto FHNeedAssessmentDto) {
        FHNeedAssessmentDto fHNeedAssessmentDto = fhNeedAssessmentService.saveNeedAssessment(FHNeedAssessmentDto);
        log.info("saveNeedAssessment:" + FHNeedAssessmentDto);
        return new ResponseEntity<>(fHNeedAssessmentDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readNeedAssessment/{fhNeedAssessmentId}", produces = "application/json")
    @ApiOperation("Read FH NeedAssessment")
    @ResponseStatus(HttpStatus.OK)
    public FHNeedAssessmentDto readNeedAssessment(@PathVariable("fhNeedAssessmentId") Long fhNeedAssessmentId) {
        log.info("readNeedAssessment fhNeedAssessmentId :" + fhNeedAssessmentId);
        return fhNeedAssessmentService.readNeedAssessment(fhNeedAssessmentId);
    }

    @GetMapping(value = "/readAllNeedAssessment/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read All FH NeedAssessment")
    @ResponseStatus(HttpStatus.OK)
    public List<FHNeedAssessmentDto> readAllNeedAssessment(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("ReadAllNeedAssessment fhFileDetailsId :" + fhFileDetailsId);
        return fhNeedAssessmentService.readAllNeedAssessment(fhFileDetailsId);
    }

    @DeleteMapping("/removeNeedAssessment/{fhNeedAssessmentId}")
    @ApiOperation("Remove FH NeedAssessment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeNeedAssessment(@PathVariable("fhNeedAssessmentId") Long fhNeedAssessmentId) {
        log.info("RemoveNeedAssessment By fhNeedAssessmentId :" + fhNeedAssessmentId);
        fhNeedAssessmentService.removeNeedAssessment(fhNeedAssessmentId);
    }
}
