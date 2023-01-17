package org.cyfwms.culturalprogram.api;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.culturalprogram.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.cyfwms.culturalprogram.service.CPASearchService;
import org.cyfwms.culturalprogram.service.CPAIdentityService;
import org.cyfwms.culturalprogram.service.CPAParticipantSearchService;
import org.cyfwms.culturalprogram.service.CPAParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/v1/culturalprogandactservice")
@CrossOrigin("*")
public class CulturalProgramController {
    @Autowired
    CPAParticipantSearchService CPAParticipantSearchService;
    @Autowired
    CPASearchService CPASearchService;
    @Autowired
    private CPAIdentityService CPAIdentityService;
    @Autowired
    private CPAParticipantService CPAParticipantService;

    @GetMapping(value = "/readCulturalProgAndAct/{culturalprogramid}", produces = "application/json")
    @ApiOperation("Read CulturalProgAndAct")
    @ResponseStatus(HttpStatus.OK)
    public CPAIdentityDto readCpaIdentity(@PathVariable("culturalprogramid") Long culturalProgramId) {
        log.info("Read CulturalProgAndActIdentity "+"CulturalProgramId :"+culturalProgramId);
        return CPAIdentityService.readCpaIdentity(culturalProgramId);
    }

    @PutMapping(value = "/saveCulturalProgAndAct", produces = "application/json")
    @ApiOperation("Save or Update cultural program")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CPAIdentityDto> saveCpaIdentity(@RequestBody CPAIdentityDto CPAIdentityDto) {
       CPAIdentityDto cpaIdentityDto = CPAIdentityService.saveCpaIdentity(CPAIdentityDto);
       log.info("Save or Update Cultural Program "+CPAIdentityDto);
       return new ResponseEntity<>(cpaIdentityDto,HttpStatus.CREATED);
    }

    @GetMapping(value = {"/culturalProgAndActSearch/{referenceId}/{name}/{type}/{caseworker}/{startDate}/{status}"},produces = "application/json")
    @ApiOperation("Search culturalProgAndAct")
    @ResponseStatus(HttpStatus.OK)
    public List<CPASearchResultsDto> searchCulturalProgAndAct(@PathVariable Map<String, String> var) {
        CPASearchDto CPASearchDto = new CPASearchDto();
        CPASearchDto.setReferenceId(("null".equals(var.get("referenceId"))
                || var.get("referenceId") == null) ?null:Long.parseLong(var.get("referenceId")));
        CPASearchDto.setName(
                ("null".equals(var.get("name"))
                        || var.get("name") == null) ?null:var.get("name"));

        CPASearchDto.setType(
                ("null".equals(var.get("type"))
                        || var.get("type") == null) ?null:var.get("type"));

        CPASearchDto.setCaseworker(
                ("null".equals(var.get("caseworker"))
                        || var.get("caseworker") == null) ?null:var.get("caseworker"));

        LocalDate dateTime=null;
        if(!"null".equals(var.get("startDate"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateTime = LocalDate.parse(var.get("startDate"), formatter);
        }
        CPASearchDto.setStartDate(dateTime);

        CPASearchDto.setStatus(
                ("null".equals(var.get("status"))
                        || var.get("status") == null) ?null:var.get("status"));
        log.info("SearchCulturalProgAndAct "+CPASearchDto);
        return CPASearchService.searchCulturalProgAndAct(CPASearchDto);
    }

    @DeleteMapping("/removeCulturalProgAndAct/{culturalprogramid}")
    @ApiOperation("Remove CulturalProgAndAct")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> removeCpaIdentity(@PathVariable("culturalprogramid") Long culturalProgramId) {
         CPAIdentityService.removeCpaIdentity(culturalProgramId);
        log.info("Remove CulturalProgAndAct "+"CulturalProgramId :"+culturalProgramId);
        return new ResponseEntity<>("Operation Successful",HttpStatus.ACCEPTED);
    }



    @GetMapping(value = "/readParticipantsCulturalAndAct/{culturalprogramid}", produces = "application/json")
    @ApiOperation("Read participantsCulturalAndAct")
    @ResponseStatus(HttpStatus.OK)
    public CPAParticipantDto readCpaParticipant(@PathVariable("culturalprogramid") Long culturalProgramId) {
        log.info("Read participantsCulturalAndAct "+"culturalProgramId :"+culturalProgramId);
        return CPAParticipantService.readCpaParticipant(culturalProgramId);
    }

    @PutMapping(value = "/saveParticipantCulturalProg", produces = "application/json")
    @ApiOperation("Save or Update cultural program")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CPAParticipantDto> saveCpaParticipant(@RequestBody CPAParticipantDto CPAParticipantDto) {
        CPAParticipantDto cpaParticipantDto = CPAParticipantService.saveCpaParticipant(CPAParticipantDto);
        log.info("Save or Update participantsCulturalAndAct "+CPAParticipantDto);
        return new ResponseEntity<>(cpaParticipantDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/removeParticipantCulturalProg/{participantculturalprogid}")
    @ApiOperation("Remove ParticipantCulturalProg")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeCpaParticipant(@PathVariable("participantculturalprogid") Long participantCulturalProId) {
         CPAParticipantService.removeCpaParticipant(participantCulturalProId);
        log.info("Remove ParticipantsCulturalAndAct "+"ParticipantCulturalProId"+participantCulturalProId);
        return new ResponseEntity<>("Operation Successful",HttpStatus.OK);

    }

    @GetMapping(value = {"/participantCulturalProgSearch/{culturalProgramId}/{data}"},produces = "application/json")
    @ApiOperation("Search culturalProgram")
    @ResponseStatus(HttpStatus.OK)
    public List<CPAParticipantSearchResultsDto> searchCulturalProgram(@PathVariable Map<String, String> var)
    {
        CPASearchCriteriaDto participantCulturalSearchDto =new CPASearchCriteriaDto();
        participantCulturalSearchDto.setCulturalProgramId(("null".equals(var.get("culturalProgramId"))
                || var.get("culturalProgramId") == null) ? null : Long.parseLong(var.get("culturalProgramId")));
        participantCulturalSearchDto.setData(
                ("null".equals(var.get("data"))
                        || var.get("data") == null) ?null:var.get("data"));
        log.info("Search CulturalProgram"+participantCulturalSearchDto);
        return CPAParticipantSearchService.searchParticipantCulturalProgAndAct(participantCulturalSearchDto);
    }
}
