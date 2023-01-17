package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHSafetyPlanDto;
import org.cyfwms.familyhealing.service.FHSafetyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j(topic = "SafetyPlanController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FHSafetyPlanController {
    @Autowired
    private FHSafetyPlanService fhSafetyPlanService;

    @PutMapping(value = "/saveSafetyPlan", produces = "application/json")
    @ApiOperation("Save or Update  SafetyPlan")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHSafetyPlanDto> saveSafetyPlan(@RequestBody FHSafetyPlanDto FHSafetyPlanDto) {
        FHSafetyPlanDto fhSafetyPlanDto = fhSafetyPlanService.saveSafetyPlan(FHSafetyPlanDto);
        log.info("saveSafetyPlan:" + FHSafetyPlanDto);
        return new ResponseEntity<>(fhSafetyPlanDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readSafetyPlan/{fhSafetyPlanId}", produces = "application/json")
    @ApiOperation("Read FH SafetyPlan")
    @ResponseStatus(HttpStatus.OK)
    public FHSafetyPlanDto readSafetyPlan(@PathVariable("fhSafetyPlanId") Long fhSafetyPlanId) {
        log.info("readSafetyPlan fhSafetyPlanId :" + fhSafetyPlanId);
        return fhSafetyPlanService.readSafetyPlan(fhSafetyPlanId);
    }

    @GetMapping(value = "/readAllSafetyPlan/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read All FH CasePlan")
    @ResponseStatus(HttpStatus.OK)
    public List<FHSafetyPlanDto> readAllSafetyPlan(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("readAllSafetyPlan fhFileDetailsId :" + fhFileDetailsId);
        return fhSafetyPlanService.readAllSafetyPlan(fhFileDetailsId);
    }

    @DeleteMapping("/removeSafetyPlan/{fhSafetyPlanId}")
    @ApiOperation("Remove FH CasePlan")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeSafetyPlan(@PathVariable("fhSafetyPlanId") Long fhSafetyPlanId) {
        log.info("RemoveSafetyPlan By fhSafetyPlanId :" + fhSafetyPlanId);
        fhSafetyPlanService.removeSafetyPlan(fhSafetyPlanId);
    }
}
