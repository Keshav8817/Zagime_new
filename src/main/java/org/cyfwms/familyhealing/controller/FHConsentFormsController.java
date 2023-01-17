package org.cyfwms.familyhealing.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHConsentFormsDto;
import org.cyfwms.familyhealing.dto.FHSafetyPlanDto;
import org.cyfwms.familyhealing.service.FHConsentFormsService;
import org.cyfwms.initialcontact.dto.ICAttachmentDTO;
import org.cyfwms.participant.dto.ParticipantIdentityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j(topic = "ConsentFormsController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FHConsentFormsController {
    @Autowired
    private FHConsentFormsService fhConsentFormsService;


    @PutMapping("/saveConsentForms")
    @ApiOperation("Save or Update ConsentForms")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHConsentFormsDto> saveConsentForms(@RequestParam Map<String, String> params,
                                                                          @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws IOException {
        FHConsentFormsDto fhConsentFormsDto = new FHConsentFormsDto();
        mapConsentFormsData(fhConsentFormsDto, params);
        fhConsentFormsDto = fhConsentFormsService.saveConsentForms(fhConsentFormsDto, multipartFile);
        log.info("SaveConsentForms :" + fhConsentFormsDto);
        return ResponseEntity.ok(fhConsentFormsDto);
    }

    @GetMapping(value = "/readConsentForms/{fhConsentFormsId}", produces = "application/json")
    @ApiOperation("Read FH ConsentForms")
    @ResponseStatus(HttpStatus.OK)
    public FHConsentFormsDto readConsentForms(@PathVariable("fhConsentFormsId") Long fhConsentFormsId) {
        log.info("ReadConsentForms fhConsentFormsId :" + fhConsentFormsId);
        return fhConsentFormsService.readConsentForms(fhConsentFormsId);
    }

    @GetMapping(value = "/readAllConsentForms/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read All FH ConsentForms")
    @ResponseStatus(HttpStatus.OK)
    public List<FHConsentFormsDto> readAllConsentForms(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("readAllConsentForms fhFileDetailsId :" + fhFileDetailsId);
        return fhConsentFormsService.readAllConsentForms(fhFileDetailsId);
    }

    @DeleteMapping("/removeConsentForms/{fhConsentFormsId}")
    @ApiOperation("Remove FH ConsentForms")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeConsentForms(@PathVariable("fhConsentFormsId") Long fhConsentFormsId) {
        log.info("RemoveConsentForms By fhConsentFormsId :" + fhConsentFormsId);
        fhConsentFormsService.removeConsentForms(fhConsentFormsId);
    }

    private void mapConsentFormsData(FHConsentFormsDto fhConsentFormsDto, Map<String, String> params) {
        log.info("Inside ConsentFormsData");
        long fhConsentFormsId = 0;
        if (params.get("fhConsentFormsId") != null &&
                !params.get("fhConsentFormsId").equals("undefined")
                && params.get("fhConsentFormsId").length() > 0) {
            fhConsentFormsId = Long.parseLong(params.get("fhConsentFormsId"));
        }
        fhConsentFormsDto.setFhConsentFormsId(fhConsentFormsId);
        fhConsentFormsDto.setDate(LocalDate.parse(params.get("date")));
        fhConsentFormsDto.setType(params.get("type"));
        fhConsentFormsDto.setPleaseSpecify(params.get("pleaseSpecify"));
        fhConsentFormsDto.setFhFileDetailsId(Long.parseLong(params.get("fhFileDetailsId")));
        log.info("Exit ConsentForms");
    }



}
