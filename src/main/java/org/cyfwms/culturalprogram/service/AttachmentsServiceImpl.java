package org.cyfwms.culturalprogram.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.cyfwms.common.entity.Attachment;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.culturalprogram.dto.AttachmentDTO;
import org.cyfwms.culturalprogram.entity.AttachmentEntity;
import org.cyfwms.culturalprogram.repository.AttachmentsRepository;
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

@AllArgsConstructor
@Service
public class AttachmentsServiceImpl implements AttachmentsService {
    @Autowired
    private AttachmentsRepository attachmentsRepository;

    @Autowired
    private MessageUtil messageUtil;


    private AttachmentEntity readCulturalAttachment(Long culturalProgramId) {
        AttachmentEntity attachmentEntity = attachmentsRepository.findById(culturalProgramId).filter(p -> p.getStatus().equals("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(culturalProgramId))));
        ;
        return attachmentEntity;
    }

    @Override
    public AttachmentDTO uploadAttachment(MultipartFile file, String culturalDto) throws IOException {
        Attachment attachment = null;
        AttachmentDTO attachmentDTO = new ObjectMapper().readValue(culturalDto, AttachmentDTO.class);
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachment = new Attachment();
        long culturalProgAttachmentId = attachmentDTO.getCulturalProgAttachmentId();
        if (attachmentDTO.getCulturalProgAttachmentId() == 0) {
            attachmentEntity.setStatus("ACTIVE");
            BeanUtils.copyProperties(attachmentDTO, attachmentEntity);
        } else {
            attachmentEntity = readCulturalAttachment(culturalProgAttachmentId);
            attachmentDTO.setCulturalProgAttachmentId(attachmentDTO.getCulturalProgAttachmentId());
            if (attachmentEntity.getAttachment() != null) {
                attachment.setAttachmentId(attachmentEntity.getAttachment().getAttachmentId());
                attachment.setReceiptDate(attachmentEntity.getAttachment().getReceiptDate());
            }
            BeanUtils.copyProperties(attachmentDTO, attachmentEntity);
        }

        if (file != null) {
            validateCpAttachment(file);
            attachment.setAttachmentContents(file.getBytes());
            attachment.setAttachmentName(file.getOriginalFilename());
            attachment.setAttachmentStatus("ACTIVE");
            attachment.setDocumentType(file.getContentType());
            attachmentEntity.setAttachment(attachment);
        }
        attachmentEntity = attachmentsRepository.save(attachmentEntity);
        attachmentDTO.setCulturalAttachmentName(attachment.getAttachmentName());
        attachmentDTO.setAttachmentType(attachment.getDocumentType());
        attachmentDTO.setFile(attachment.getAttachmentContents());
        attachmentDTO.setCulturalProgAttachmentId(attachmentEntity.getCulturalProgAttachmentId());
        return attachmentDTO;
    }

    private void validateCpAttachment(MultipartFile file) throws IOException {
        boolean invalidCpaAttachment = true;

        if (file.getContentType().equals("image/png") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
                file.getContentType().equals("image/jpg") || file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("application/pdf") || file.getContentType().equals("application/vnd.ms-excel") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                file.getContentType().equals("image/bmp") ||
                file.getContentType().equals("image/gif")) {
            invalidCpaAttachment = false;
        }
        if (invalidCpaAttachment) {
            throw new ResponseStatusException(
                    INTERNAL_SERVER_ERROR, " PNG DOCUMENT JPG PDF SHEET BMP AND GIF content type are allowed");
        }
    }


    @Override
    public AttachmentDTO getOneFile(Long id) {
        AttachmentDTO culturalImageDto = new AttachmentDTO();
        AttachmentEntity attachmentEntity = readCulturalAttachment(id);
        culturalImageDto.setCulturalProgAttachmentId(attachmentEntity.getCulturalProgAttachmentId());
        culturalImageDto.setCulturalProgramId(attachmentEntity.getCulturalProgramId());
        culturalImageDto.setType(attachmentEntity.getType());
        culturalImageDto.setName(attachmentEntity.getName());
        if (attachmentEntity.getAttachment() != null) {
            culturalImageDto.setAttachmentType(attachmentEntity.getAttachment().getDocumentType());
            culturalImageDto.setFile(attachmentEntity.getAttachment().getAttachmentContents());
            culturalImageDto.setCulturalAttachmentName(attachmentEntity.getAttachment().getAttachmentName());
        }
        return culturalImageDto;
    }

    @Override
    public void removeCulturalProgAttachment(Long culturalProgAttachmentId) {
        AttachmentEntity attachmentEntity = readCulturalAttachment(culturalProgAttachmentId);
        attachmentEntity.setStatus("INACTIVE");
        attachmentsRepository.save(attachmentEntity);
    }


    @Override
    public List<AttachmentDTO> getAllFiles(Long culturalProgramId) {
        List<AttachmentDTO> culturalProgImageDtoList = new ArrayList<AttachmentDTO>();

        culturalProgImageDtoList = attachmentsRepository.findByCulturalProgramId(culturalProgramId)
                .stream()
                .map(attachment -> {
                    AttachmentDTO attachDto = new AttachmentDTO();
                    BeanUtils.copyProperties(attachment, attachDto);
                    if (attachment.getAttachment() != null) {
                        attachDto.setCulturalAttachmentName(attachment.getAttachment().getAttachmentName());
                        attachDto.setAttachmentType(attachment.getAttachment().getDocumentType());
                    }
                    return attachDto;
                }).collect(Collectors.toList());
        return culturalProgImageDtoList;
    }


}