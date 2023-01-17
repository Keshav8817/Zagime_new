package org.cyfwms.initialcontact.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.cyfwms.initialcontact.dto.ICAttachmentDTO;
import org.cyfwms.initialcontact.dto.ICCommonDataDto;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICSearchCriteriaDto;
import org.cyfwms.initialcontact.dto.ICSearchResultsDto;
import org.cyfwms.initialcontact.service.ICAttachmentService;
import org.cyfwms.initialcontact.service.ICCommonDataService;
import org.cyfwms.initialcontact.service.ICFileDetailsService;
import org.cyfwms.initialcontact.service.ICSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/initialcontactservice")
@CrossOrigin("*")
@Slf4j
public class InitialContactController {
    @Autowired
    ICFileDetailsService fileDetailsService;
    @Autowired
    ICSearchService searchService;
    @Autowired
    ICCommonDataService iCCommonDataService;

    @Autowired
    private ICAttachmentService icAttachmentService;

    @ApiOperation("Search Initial Contact(s)")
    @GetMapping(value = {
            "/search/{clientname}/{fileNumber}/{caseworker}/{startingDate}/{status}" }, produces = "application/json")
    @ResponseStatus(OK)
    public List<ICSearchResultsDto> search(@PathVariable Map<String, String> var) {
        ICSearchCriteriaDto initialContactSearchCriteriaDto = new ICSearchCriteriaDto();
        initialContactSearchCriteriaDto.setClientName(
                ("null".equals(var.get("clientname"))
                        || var.get("clientname") == null) ? null : var.get("clientname"));
        initialContactSearchCriteriaDto.setFileNumber(("null".equals(var.get("fileNumber"))
                || var.get("fileNumber") == null) ? null : Long.parseLong(var.get("fileNumber")));
        initialContactSearchCriteriaDto.setCaseworker(
                ("null".equals(var.get("caseworker"))
                        || var.get("caseworker") == null) ? null : var.get("caseworker"));
        LocalDate dateTime = null;
        if (!"null".equals(var.get("startingDate"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateTime = LocalDate.parse(var.get("startingDate"), formatter);
        }
        initialContactSearchCriteriaDto.setStartingDate(dateTime);
        initialContactSearchCriteriaDto.setStatus(
                ("null".equals(var.get("status"))
                        || var.get("status") == null) ? null : var.get("status"));
        log.info("Search Initial Contact " + initialContactSearchCriteriaDto);
        return searchService.search(initialContactSearchCriteriaDto);
    }

    @ApiOperation("Soft delete Initial Contact")
    @DeleteMapping("/remove/{fileDetailsId}")
    public ResponseEntity<String> removeICFileDetails(@PathVariable("fileDetailsId") Long fileDetailsId) {
        fileDetailsService.removeICFileDetails(fileDetailsId);
        log.info("RemoveICFileDetails " + "fileDetailsId:" + fileDetailsId);
        return new ResponseEntity("Operation Successful", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/readAll/{fileNumber}", produces = "application/json")
    @ApiOperation("Read All Output InitialContact")
    @ResponseStatus(HttpStatus.OK)
    public ICCommonDataDto iCCommonData(@PathVariable("fileNumber") Long fileNumber) {
        log.info("Read all iCCommonData " + "FileNumber:" + fileNumber);
        return iCCommonDataService.iCCommonData(fileNumber);
    }

    @ApiOperation("Save/Upload/Put one/single attachment.")
    @PutMapping("/attachments/save_one")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ICAttachmentDTO> saveOne(@RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("icDto") String icDto) throws IOException {
        ICAttachmentDTO iCAttachmentDTO = icAttachmentService.uploadAttachment(file, icDto);
        log.info("Save/Upload/Put One/Single Attachment in Initial Contact :"+iCAttachmentDTO);
        return ResponseEntity.ok(iCAttachmentDTO);
    }

    @ApiOperation("Read/Get one/single attachment.")
    @GetMapping("/attachments/read_one/{icattchmentid}")
    public ICAttachmentDTO readOne(@PathVariable("icattchmentid") Long icAttachmentId) {
        log.info("Read/Get One/Single Attachment by ICAttachmentId"+icAttachmentId);
        return icAttachmentService.getOneFile(icAttachmentId);
    }

    @ApiOperation("Read/Get all attachments.")
    @GetMapping(value = "/attachments/read_all/{filedetailsid}", produces = "application/json")
    @ResponseStatus(OK)
    public List<ICAttachmentDTO> readAll(@PathVariable("filedetailsid") Long fileDetailsId) {
        log.info("Read/Get All Attachments :"+fileDetailsId);
        return icAttachmentService.getAllFiles(fileDetailsId);
    }


    @DeleteMapping("/attachments/remove_one/{icattchmentid}")
    @ApiOperation("Soft remove/delete one/single attachment.")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeICAttachment(@PathVariable("icattchmentid") Long icAttachmentId) {
        log.info("Soft Remove/Delete One/Single Attachment By ICAttachmentID :"+icAttachmentId);
        icAttachmentService.removeICAttachment(icAttachmentId);
    }
}
