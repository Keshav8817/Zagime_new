package org.cyfwms.familyhealing.controller;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHFileDetailsDto;
import org.cyfwms.familyhealing.dto.FHFileDetailsSearchCriteriaDto;
import org.cyfwms.familyhealing.dto.FHFileDetailsSearchResultDto;
import org.cyfwms.familyhealing.service.FHFileDetailsSearchService;
import org.cyfwms.familyhealing.service.FHFileDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
@RestController
@Slf4j(topic = "FileDetailsController")
@AllArgsConstructor
@RequestMapping("/v1/familyHealing/service")
@CrossOrigin("*")
public class FileDetailsController {
    @Autowired
    private FHFileDetailsService fileDetailsService;

    @Autowired
    private FHFileDetailsSearchService fileDetailsSearchService;

    @GetMapping(value = "/readFileDetails/{fhFileDetailsId}", produces = "application/json")
    @ApiOperation("Read FHFileDetails")
    @ResponseStatus(HttpStatus.OK)
    public FHFileDetailsDto readFileDetails(@PathVariable("fhFileDetailsId") Long fhFileDetailsId) {
        log.info("ReadFHFileDetails fhFileDetailsId :" + fhFileDetailsId);
        return fileDetailsService.readFileDetails(fhFileDetailsId);
    }

    @PutMapping(value = "/saveFileDetails", produces = "application/json")
    @ApiOperation("Save or Update FHFileDetails")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FHFileDetailsDto> saveFileDetails(@RequestBody FHFileDetailsDto FHFileDetailsDto) {
        FHFileDetailsDto fHFileDetailsDto = fileDetailsService.saveFileDetails(FHFileDetailsDto);
        log.info("SaveFHFileDetails :" + FHFileDetailsDto);
        return new ResponseEntity<>(fHFileDetailsDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/removeFileDetails/{fhDetailsId}")
    @ApiOperation("Remove FHFileDetails")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeFileDetails(@PathVariable("fhDetailsId") Long fhDetailsId) {
        log.info("RemoveFHFileDetails By fileNo :" + fhDetailsId);
        fileDetailsService.removeFileDetails(fhDetailsId);
    }

    @GetMapping(value = {"/searchFileDetails/{clientName}/{fileNo}/{status}/{community}/{caseworker}/{startDate}"}, produces = "application/json")
    @ApiOperation("Search FHFileDetails")
    @ResponseStatus(HttpStatus.OK)
    public List<FHFileDetailsSearchResultDto> searchFileDetails(
            @PathVariable Map<String, String> var) {
        FHFileDetailsSearchCriteriaDto fileDetailsSearchCriteriaDto = getFHFileDetailsSearchCriteriaDto(var);
        log.info("SearchFHFileDetails :" + fileDetailsSearchCriteriaDto);
        return fileDetailsSearchService.searchFileDetails(fileDetailsSearchCriteriaDto);
    }

    private FHFileDetailsSearchCriteriaDto getFHFileDetailsSearchCriteriaDto(Map<String, String> var) {
        log.info("Inside GetFHFileDetailsSearchCriteriaDto");
        FHFileDetailsSearchCriteriaDto fileDetailsSearchCriteriaDto = new FHFileDetailsSearchCriteriaDto();
        LocalDate dateTime = null;
        if (!"null".equals(var.get("startDate"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateTime = LocalDate.parse(var.get("startDate"), formatter);
        }

        fileDetailsSearchCriteriaDto.setClientName(
                ("null".equals(var.get("clientName"))
                        || var.get("clientName") == null) ? null : var.get("clientName"));

        fileDetailsSearchCriteriaDto.setFileNo(("null".equals(var.get("fileNo"))
                || var.get("fileNo") == null) ? null : Long.parseLong(var.get("fileNo")));

        fileDetailsSearchCriteriaDto.setStatus(
                ("null".equals(var.get("status"))
                        || var.get("status") == null) ? null : var.get("status"));

        fileDetailsSearchCriteriaDto.setCommunity(
                ("null".equals(var.get("community"))
                        || var.get("community") == null) ? null : var.get("community"));

        fileDetailsSearchCriteriaDto.setCaseworker(
                ("null".equals(var.get("caseworker"))
                        || var.get("caseworker") == null) ? null : var.get("caseworker"));

        fileDetailsSearchCriteriaDto.setStartDate(dateTime);
        log.info("Exit GetFHFileDetailsSearchCriteriaDto");
        return fileDetailsSearchCriteriaDto;
    }
}
