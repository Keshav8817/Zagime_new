package org.cyfwms.participant.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.ParticipantAttachmentDto;
import org.cyfwms.participant.service.ParticipantAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j(topic = "ParticipantAttachmentController")
@AllArgsConstructor
@RequestMapping("/v1/participantservice/attachments")
@CrossOrigin("*")
public class ParticipantAttachmentController {

    @Autowired
    private ParticipantAttachmentService participantAttachmentService;

    @ApiOperation("Save/Upload/Put one/single attachment.")
    @PutMapping("/save_one")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ParticipantAttachmentDto> saveOne(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("participantDto") String participantDto) throws IOException {
        ParticipantAttachmentDto participantAttachmentTabDto = participantAttachmentService
                .uploadParticipantAttachment(file, participantDto);
        log.info("Save/Upload/Put One/Single Attachment :"+participantAttachmentTabDto);
        return ResponseEntity.ok(participantAttachmentTabDto);
    }

    @ApiOperation("Read/Get one/single attachment.")
    @GetMapping("/read_one/{participantAttachmentId}")
    public ParticipantAttachmentDto readOne(@PathVariable Long participantAttachmentId)
    {
        log.info("Read/Get One/Single Attachment "+"ParticipantAttachmentId :"+participantAttachmentId);
        return participantAttachmentService.getOneFile(participantAttachmentId);
    }

    @ApiOperation("Read/Get all attachments.")
    @GetMapping(value = "/read_all/{participantId}", produces = "application/json")
    @ResponseStatus(OK)
    public List<ParticipantAttachmentDto> readAll(@PathVariable("participantId") Long participantId) {
        log.info("Read/Get all Attachments "+"ParticipantId :"+participantId);
        return participantAttachmentService.getAllFiles(participantId);
    }

    @DeleteMapping("/remove_one/{participantAttachmentId}")
    @ApiOperation("Soft remove/delete one/single attachment.")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeParticipantAttachment(@PathVariable("participantAttachmentId") Long participantAttachmentId) {
        log.info("Remove/Delete One/Single Attachment "+"ParticipantAttachmentId :"+participantAttachmentId);
        participantAttachmentService.removeParticipantAttachment(participantAttachmentId);
    }
    

    @ApiOperation("Downlaod/Get one attachments.")
    @GetMapping("/download_one/{participantAttachmentId}")
    @ResponseBody
    public HttpEntity<byte[]> downloadOne(@PathVariable Long participantAttachmentId, HttpServletResponse response) {
        ParticipantAttachmentDto participantAttachmentDto = participantAttachmentService.downloadOne(participantAttachmentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(participantAttachmentDto.getAttachment().getAttachmentContents().length);
        response.setHeader("Content-Disposition", "attachment; filename=" + participantAttachmentDto.getAttachment().getAttachmentName());
        log.info("Read/Download One/Single Attachment "+"ParticipantAttachmentId :"+participantAttachmentId);
        return new HttpEntity<byte[]>(participantAttachmentDto.getAttachment().getAttachmentContents(), headers);
    }


}
