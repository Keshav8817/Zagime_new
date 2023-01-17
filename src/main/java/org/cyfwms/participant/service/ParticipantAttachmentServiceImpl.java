package org.cyfwms.participant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.entity.Attachment;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.participant.dto.ParticipantAttachmentDto;
import org.cyfwms.participant.entity.ParticipantAttachment;
import org.cyfwms.participant.repository.ParticipantAttachmentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@AllArgsConstructor
@Slf4j
public class ParticipantAttachmentServiceImpl implements ParticipantAttachmentService {

    @Autowired
    private ParticipantAttachmentRepo participantAttachmentRepo;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public ParticipantAttachmentDto readParticipantAttachmentByTypeAndStatus(
            Long participantId, String attachmentType, String status) {

        log.info("Inside ReadParticipantAttachmentByTypeAndStatus");
        ParticipantAttachmentDto participantAttachmentDto =
                new ParticipantAttachmentDto();
        List<ParticipantAttachment> participantAttachList = participantAttachmentRepo
                .findByParticipantIdAndAttachmentTypeAndStatus(
                        participantId, attachmentType, status);

        if (!participantAttachList.isEmpty()) {
            BeanUtils.copyProperties(
                    participantAttachList.get(0), participantAttachmentDto);

        }
        log.info("Exit ReadParticipantAttachmentByTypeAndStatus");
        return participantAttachmentDto;
    }

    @Override
    public ParticipantAttachmentDto saveParticipantAttachment(ParticipantAttachmentDto participantAttachmentDto) {
        log.info("Inside SaveParticipantAttachment");
        ParticipantAttachment participantAttachment = new ParticipantAttachment();
        BeanUtils.copyProperties(participantAttachmentDto, participantAttachment);
        participantAttachment = participantAttachmentRepo.save(participantAttachment);
        participantAttachmentDto.setParticipantAttachmentId(
                participantAttachment.getParticipantAttachmentId());
        participantAttachmentDto.setAttachment(participantAttachment.getAttachment());
        log.info("Exit SaveParticipantAttachment");
        return participantAttachmentDto;
    }

    @Override
    public ParticipantAttachmentDto uploadParticipantAttachment(MultipartFile file, String participantDto)
            throws IOException {
        log.info("Inside UploadParticipantAttachment");
        Attachment attachment = null;
        ParticipantAttachmentDto participantAttachmentDto = new ObjectMapper().readValue(participantDto,
                ParticipantAttachmentDto.class);
        ParticipantAttachment participantAttachment = new ParticipantAttachment();

        attachment = new Attachment();
        long participantAttachmentId = participantAttachmentDto.getParticipantAttachmentId();
        if (participantAttachmentDto.getParticipantAttachmentId() == 0) {
            BeanUtils.copyProperties(participantAttachmentDto, participantAttachment);
        } else {
            participantAttachment = readParticipantAttachment(participantAttachmentId);
            participantAttachmentDto.setParticipantAttachmentId(participantAttachmentDto.getParticipantAttachmentId());
            if (participantAttachment.getAttachment() != null) {
                attachment.setAttachmentId(participantAttachment.getAttachment().getAttachmentId());
                attachment.setReceiptDate(participantAttachment.getAttachment().getReceiptDate());
            }
            BeanUtils.copyProperties(participantAttachmentDto, participantAttachment);
        }

        if (file != null) {
            validateParticipantAttachment(file);
            attachment.setAttachmentContents(file.getBytes());
            attachment.setAttachmentName(file.getOriginalFilename());
            attachment.setAttachmentStatus("ACTIVE");
            attachment.setDocumentType(file.getContentType());
            participantAttachment.setAttachment(attachment);

        }
        participantAttachment.setAttachmentType("PARTICIPANT_ATTACHMENT");
        participantAttachment.setStatus("ACTIVE");
        participantAttachment = participantAttachmentRepo.save(participantAttachment);
        participantAttachmentDto.setAttachmentType(attachment.getDocumentType());
        participantAttachmentDto.setParticipantAttachmentId(participantAttachment.getParticipantAttachmentId());
        log.info("Exit UploadParticipantAttachment");
        return participantAttachmentDto;
    }

    @Override
    public void removeParticipantAttachment(Long participantAttachmentId) {
        log.info("Inside RemoveParticipantAttachment");
        ParticipantAttachment attachmentEntity = readParticipantAttachment(participantAttachmentId);
        attachmentEntity.setStatus("INACTIVE");
        participantAttachmentRepo.save(attachmentEntity);
        log.info("Exit RemoveParticipantAttachment");
    }

    @Override
    public List<ParticipantAttachmentDto> getAllFiles(Long participantId) {
        log.info("Inside GetAllFiles ParticipantAttachment");
        List<ParticipantAttachmentDto> participantAttachmentDtoList = new ArrayList<ParticipantAttachmentDto>();

        participantAttachmentDtoList = participantAttachmentRepo.findByParticipantId(participantId)
                .stream()
                .map(attachment -> {
                    ParticipantAttachmentDto attachDto = new ParticipantAttachmentDto();
                    attachDto.setParticipantAttachmentId(attachment.getParticipantAttachmentId());
                    attachDto.setParticipantId(attachment.getParticipantId());
                    attachDto.setName(attachment.getName());
                    attachDto.setType(attachment.getType());
                    if (attachment.getAttachment() != null) {
                        attachDto.setAttachmentType(attachment.getAttachment().getDocumentType());
                        attachDto.setParticipantImageName(attachment.getAttachment().getAttachmentName());

                    }
                    return attachDto;
                }).collect(Collectors.toList());
        log.info("Exit GetAllFiles ParticipantAttachment");
        return participantAttachmentDtoList;
    }

    @Override
    public ParticipantAttachmentDto getOneFile(Long participantAttachmentId) {
        log.info("Inside GetOneFiles ParticipantAttachment");
        ParticipantAttachmentDto participantAttachmentDto = new ParticipantAttachmentDto();
        ParticipantAttachment participantAttachment = readParticipantAttachment(participantAttachmentId);
        participantAttachmentDto.setParticipantAttachmentId(participantAttachment.getParticipantAttachmentId());
        participantAttachmentDto.setParticipantId(participantAttachment.getParticipantId());
        participantAttachmentDto.setType(participantAttachment.getType());
        participantAttachmentDto.setName(participantAttachment.getName());
        if (participantAttachment.getAttachment() != null) {
            participantAttachmentDto.setAttachmentType(participantAttachment.getAttachment().getDocumentType());
            participantAttachmentDto.setParticipantImageName(participantAttachment.getAttachment().getAttachmentName());
            participantAttachmentDto.setImage(participantAttachment.getAttachment().getAttachmentContents());
        }
        log.info("Exit GetOneFiles ParticipantAttachment");
        return participantAttachmentDto;
    }

    @Override
    public ParticipantAttachmentDto downloadOne(Long participantAttachmentId) {
        ParticipantAttachmentDto participantAttachmentDto=new ParticipantAttachmentDto();
        ParticipantAttachment participantAttachment=participantAttachmentRepo.findById(participantAttachmentId).
                filter(active->active.getStatus().equalsIgnoreCase("ACTIVE")).orElseThrow(() -> new NoSuchElementFoundException(messageUtil.getLocalMessage(
                        I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(participantAttachmentId))));
        BeanUtils.copyProperties(participantAttachment,participantAttachmentDto);
        return participantAttachmentDto;
    }

//    @Override
//    public byte[] downloadOne(Long participantAttachmentId) {
//        ParticipantAttachment participantAttachment=participantAttachmentRepo.findById(participantAttachmentId).
//                filter(active->active.getStatus().equalsIgnoreCase("ACTIVE")).orElseThrow(() -> new NoSuchElementFoundException(messageUtil.getLocalMessage(
//                        I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(participantAttachmentId))));
//
//        byte [] downloadFile=participantAttachment.getAttachment().getAttachmentContents();
//        return downloadFile;
//    }

    private ParticipantAttachment readParticipantAttachment(long participantAttachmentId) {
        log.info("Inside ReadParticipantAttachment");
        ParticipantAttachment participantAttachmentEntity = participantAttachmentRepo.findById(participantAttachmentId)
                .filter(p -> p.getStatus().equals("ACTIVE"))
                .orElseThrow(() -> new NoSuchElementFoundException(messageUtil.getLocalMessage(
                        I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(participantAttachmentId))));
        log.info("Exit ReadParticipantAttachment");
        return participantAttachmentEntity;
    }

    private void validateParticipantAttachment(MultipartFile file) {
        log.info("Inside validateParticipantAttachment");
        boolean invalidParticipantAttachment = true;

        if (file.getContentType().equals("image/png") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                ||
                file.getContentType().equals("image/jpg") || file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("application/pdf")
                || file.getContentType().equals("application/vnd.ms-excel") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                file.getContentType().equals("image/bmp") ||
                file.getContentType().equals("image/gif")) {
            invalidParticipantAttachment = false;
        }
        if (invalidParticipantAttachment) {
            throw new ResponseStatusException(
                    INTERNAL_SERVER_ERROR, " PNG DOCUMENT JPG PDF SHEET BMP AND GIF content type are allowed");
        }
    }
}
