package org.cyfwms.staff.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.staff.dto.StaffAttachmentDto;
import org.cyfwms.staff.service.StaffAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j(topic = "StaffAttachmentController")
@AllArgsConstructor
@RequestMapping("/v1/staffservice/attachments")
@CrossOrigin("*")
public class AttachmentController {
    @Autowired
    private StaffAttachmentService staffAttachmentService;

    @ApiOperation("Save/Upload/Put one/single attachment.")
    @PutMapping("/save_one")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StaffAttachmentDto> saveOne(@RequestParam(value = "file", required = false) MultipartFile file,
                                                      @RequestParam("staffDto") String staffDto) throws IOException {
        StaffAttachmentDto staffAttachmentDto = staffAttachmentService.saveStaffAttachment(file, staffDto);
        log.info("SaveOneStaffAttachment :" + staffAttachmentDto);
        return ResponseEntity.ok(staffAttachmentDto);

    }

    @ApiOperation("Read/Get one/single attachment.")
    @GetMapping("/read_one/{staffAttachmentId}")
    public StaffAttachmentDto readOne(@PathVariable Long staffAttachmentId) {
        log.info("Attachment ReadOne " + "staffAttachmentId :" + staffAttachmentId);
        return staffAttachmentService.getOneFile(staffAttachmentId);
    }

    @ApiOperation("Read/Get all attachments.")
    @GetMapping(value = "/read_all/{staffId}", produces = "application/json")
    @ResponseStatus(OK)
    public List<StaffAttachmentDto> readAll(@PathVariable("staffId") Long staffId) {
        log.info("Attachment ReadAll " + "staffId :" + staffId);
        return staffAttachmentService.getAllFiles(staffId);
    }

    @ApiOperation("Soft remove/delete one/single attachment.")
    @DeleteMapping("/remove_one/{staffAttachmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeOne(@PathVariable("staffAttachmentId") Long staffAttachmentId) {
        log.info("Soft Remove/Delete One/Single Attachment By staffAttachmentId:"+staffAttachmentId);
        staffAttachmentService.removeStaffFile(staffAttachmentId);
    }

}
