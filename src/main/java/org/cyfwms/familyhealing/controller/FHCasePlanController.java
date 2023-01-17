package org.cyfwms.familyhealing.controller;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHCasePlanDto;
import org.cyfwms.familyhealing.service.FHCasePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@Slf4j(topic = "CasePlanController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FHCasePlanController {
    @Autowired
    private FHCasePlanService fhCasePlanService;

    @PutMapping(value = "/saveCasePlan", produces = "application/json")
    @ApiOperation("Save or Update  CasePlan")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHCasePlanDto> saveCasePlan(@RequestBody FHCasePlanDto FHCasePlanDto) {
        FHCasePlanDto fhCasePlanDto = fhCasePlanService.saveCasePlan(FHCasePlanDto);
        log.info("saveCasePlan:" + FHCasePlanDto);
        return new ResponseEntity<>(fhCasePlanDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/readCasePlan/{fhCasePlanId}", produces = "application/json")
    @ApiOperation("Read FH CasePlan")
    @ResponseStatus(HttpStatus.OK)
    public FHCasePlanDto readCasePlan(@PathVariable("fhCasePlanId") Long fhCasePlanId) {
        log.info("readCasePlan fhCasePlanId :" + fhCasePlanId);
        return fhCasePlanService.readCasePlan(fhCasePlanId);
    }

    @GetMapping(value = "/readAllCasePlan/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read All FH CasePlan")
    @ResponseStatus(HttpStatus.OK)
    public List<FHCasePlanDto> readAllCasePlan(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("ReadAllCasePlan fhFileDetailsId :" + fhFileDetailsId);
        return fhCasePlanService.readAllCasePlan(fhFileDetailsId);
    }

    @DeleteMapping("/removeCasePlan/{fhCasePlanId}")
    @ApiOperation("Remove FH CasePlan")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeCasePlan(@PathVariable("fhCasePlanId") Long fhCasePlanId) {
        log.info("RemoveCasePlan By fhCasePlanId :" + fhCasePlanId);
        fhCasePlanService.removeCasePlan(fhCasePlanId);
    }
}
