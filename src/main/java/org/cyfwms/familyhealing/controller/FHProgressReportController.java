package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHProgressReportDto;
import org.cyfwms.familyhealing.dto.FHSafetyPlanDto;
import org.cyfwms.familyhealing.service.FHProgressReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j(topic = "ProgressReportController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FHProgressReportController {
    @Autowired
    private FHProgressReportService fhProgressReportService;

    @PutMapping(value = "/saveProgressReport", produces = "application/json")
    @ApiOperation("Save or Update  ProgressReport")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHProgressReportDto> saveProgressReport(@RequestBody FHProgressReportDto FHProgressReportDto) {
        FHProgressReportDto fhProgressReportDto = fhProgressReportService.saveProgressReport(FHProgressReportDto);
        log.info("saveProgressReport:" + fhProgressReportDto);
        return new ResponseEntity<>(fhProgressReportDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readProgressReport/{fhProgressReportId}", produces = "application/json")
    @ApiOperation("Read FH ProgressReport")
    @ResponseStatus(HttpStatus.OK)
    public FHProgressReportDto readProgressReport(@PathVariable("fhProgressReportId") Long fhProgressReportId) {
        log.info("readProgressReport fhProgressReportId :" + fhProgressReportId);
        return fhProgressReportService.readProgressReport(fhProgressReportId);
    }

    @GetMapping(value = "/readAllProgressReport/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read All FH ProgressReport")
    @ResponseStatus(HttpStatus.OK)
    public List<FHProgressReportDto> readAllProgressReport(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("readAllProgressReport fhFileDetailsId :" + fhFileDetailsId);
        return fhProgressReportService.readAllProgressReport(fhFileDetailsId);
    }

    @DeleteMapping("/removeProgressReport/{fhProgressReportId}")
    @ApiOperation("Remove FH ProgressReport")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeProgressReport(@PathVariable("fhProgressReportId") Long fhProgressReportId) {
        log.info("RemoveProgressReport By FHProgressReportId :" + fhProgressReportId);
        fhProgressReportService.removeProgressReport(fhProgressReportId);
    }
}
