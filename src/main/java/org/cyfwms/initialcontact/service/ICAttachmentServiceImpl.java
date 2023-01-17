package org.cyfwms.initialcontact.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.entity.Attachment;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.initialcontact.dto.ICAttachmentDTO;
import org.cyfwms.initialcontact.entity.ICAttachmentEntity;
import org.cyfwms.initialcontact.repository.ICAttachmentRepository;
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
@Slf4j
public class ICAttachmentServiceImpl implements ICAttachmentService {
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private ICAttachmentRepository icAttachmentRepository;

    @Override
    public ICAttachmentDTO uploadAttachment(MultipartFile file, String icDto) throws IOException {
        log.info("Inside UploadAttachment InitialContactAttachment");
        Attachment attachment = null;
        ICAttachmentDTO iCAttachmentDTO = new ObjectMapper().readValue(icDto, ICAttachmentDTO.class);
        ICAttachmentEntity iCAttachmentEntity = new ICAttachmentEntity();
        attachment = new Attachment();
        long icAttachmentId = iCAttachmentDTO.getIcAttachmentId();
        if (iCAttachmentDTO.getIcAttachmentId() == 0) {
            iCAttachmentEntity.setStatus("ACTIVE");
            BeanUtils.copyProperties(iCAttachmentDTO, iCAttachmentEntity);
        } else {
            iCAttachmentEntity = readICAttachment(icAttachmentId);
            iCAttachmentDTO.setIcAttachmentId(iCAttachmentDTO.getIcAttachmentId());
            if (iCAttachmentEntity.getAttachment() != null) {
                attachment.setAttachmentId(iCAttachmentEntity.getAttachment().getAttachmentId());
                attachment.setReceiptDate(iCAttachmentEntity.getAttachment().getReceiptDate());
            }
            BeanUtils.copyProperties(iCAttachmentDTO, iCAttachmentEntity);
        }

        if (file != null) {
            validateICAttachment(file);
            attachment.setAttachmentContents(file.getBytes());
            attachment.setAttachmentName(file.getOriginalFilename());
            attachment.setAttachmentStatus("ACTIVE");
            attachment.setDocumentType(file.getContentType());
            iCAttachmentEntity.setAttachment(attachment);
        }
        iCAttachmentEntity = icAttachmentRepository.save(iCAttachmentEntity);
        iCAttachmentDTO.setIcAttachmentName(attachment.getAttachmentName());
        iCAttachmentDTO.setIcAttachmentType(attachment.getDocumentType());
        iCAttachmentDTO.setFile(attachment.getAttachmentContents());
        iCAttachmentDTO.setIcAttachmentId(iCAttachmentEntity.getIcAttachmentId());
        log.info("Exit UploadAttachment InitialContactAttachment");
        return iCAttachmentDTO;
    }

    @Override
    public ICAttachmentDTO getOneFile(Long icAttachmentId) {
        log.info("Inside GetOneFile InitialContactAttachment");
        ICAttachmentDTO iCAttachmentDTO = new ICAttachmentDTO();
        ICAttachmentEntity iCAttachmentEntity = readICAttachment(icAttachmentId);
        iCAttachmentDTO.setIcAttachmentId(iCAttachmentEntity.getIcAttachmentId());
        iCAttachmentDTO.setFileDetailsId(iCAttachmentEntity.getFileDetailsId());
        iCAttachmentDTO.setType(iCAttachmentEntity.getType());
        iCAttachmentDTO.setName(iCAttachmentEntity.getName());
        if (iCAttachmentEntity.getAttachment() != null) {
            iCAttachmentDTO.setIcAttachmentType(iCAttachmentEntity.getAttachment().getDocumentType());
            iCAttachmentDTO.setFile(iCAttachmentEntity.getAttachment().getAttachmentContents());
            iCAttachmentDTO.setIcAttachmentName(iCAttachmentEntity.getAttachment().getAttachmentName());
        }
        log.info("Exit GetOneFile InitialContactAttachment");
        return iCAttachmentDTO;
    }

    @Override
    public List<ICAttachmentDTO> getAllFiles(Long fileDetailsId) {
        log.info("Inside GetAllFile InitialContactAttachment");
        List<ICAttachmentDTO> iCAttachmentDtoList = new ArrayList<ICAttachmentDTO>();
        iCAttachmentDtoList = icAttachmentRepository.findByFileDetailsId(fileDetailsId)
                .stream()
                .map(attachment -> {
                    ICAttachmentDTO attachDto = new ICAttachmentDTO();
                    BeanUtils.copyProperties(attachment, attachDto);
                    if (attachment.getAttachment() != null) {
                        attachDto.setIcAttachmentName(attachment.getAttachment().getAttachmentName());
                        attachDto.setIcAttachmentType(attachment.getAttachment().getDocumentType());
                    }
                    return attachDto;
                }).collect(Collectors.toList());
        log.info("Exit GetAllFile InitialContactAttachment");
        return iCAttachmentDtoList;
    }

    @Override
    public void removeICAttachment(Long icAttachmentId) {
        log.info("Inside RemoveICAttachment InitialContactAttachment");
        ICAttachmentEntity iCAttachmentEntity = readICAttachment(icAttachmentId);
        iCAttachmentEntity.setStatus("INACTIVE");
        icAttachmentRepository.save(iCAttachmentEntity);
        log.info("Exit RemoveICAttachment InitialContactAttachment");
    }

    private ICAttachmentEntity readICAttachment(long icAttachmentId) {
        log.info("Inside ReadICAttachment InitialContactAttachment");
        ICAttachmentEntity iCAttachmentEntity = icAttachmentRepository.findById(icAttachmentId).filter(p -> p.getStatus().equals("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(icAttachmentId))));
        log.info("Exit ReadICAttachment InitialContactAttachment");
        return iCAttachmentEntity;
    }


    private void validateICAttachment(MultipartFile file) {
        log.info("Inside ValidateICAttachment InitialContactAttachment");
        boolean invalidIcAttachment = true;

        if (file.getContentType().equals("image/png") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
                file.getContentType().equals("image/jpg") || file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("application/pdf") || file.getContentType().equals("application/vnd.ms-excel") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                file.getContentType().equals("image/bmp") ||
                file.getContentType().equals("image/gif")) {
            invalidIcAttachment = false;
        }
        if (invalidIcAttachment) {
            throw new ResponseStatusException(
                    INTERNAL_SERVER_ERROR, " PNG DOCUMENT JPG PDF SHEET BMP AND GIF content type are allowed");
        }
        log.info("Exit ValidateICAttachment InitialContactAttachment");
    }
}
