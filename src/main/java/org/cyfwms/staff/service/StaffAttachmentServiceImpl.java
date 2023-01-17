package org.cyfwms.staff.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.entity.Attachment;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.StaffAttachmentDto;
import org.cyfwms.staff.entity.StaffAttachment;
import org.cyfwms.staff.repository.StaffAttachmentRepo;
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
public class StaffAttachmentServiceImpl implements StaffAttachmentService {
    @Autowired
    private StaffAttachmentRepo staffAttachmentRepo;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public StaffAttachmentDto saveStaffAttachment(MultipartFile file, String staffDto) throws IOException {
        Attachment attachment = null;
        StaffAttachmentDto staffAttachmentDto = new ObjectMapper().readValue(staffDto, StaffAttachmentDto.class);
        StaffAttachment staffAttachmentEntity = new StaffAttachment();
        attachment = new Attachment();
        long staffImageId = staffAttachmentDto.getStaffAttachmentId();
        if (staffAttachmentDto.getStaffAttachmentId() == 0) {
            staffAttachmentEntity.setStatus("ACTIVE");
            BeanUtils.copyProperties(staffAttachmentDto, staffAttachmentEntity);
        } else {
            staffAttachmentEntity = readStaffAttachment(staffImageId);
            staffAttachmentDto.setStaffAttachmentId(staffAttachmentDto.getStaffAttachmentId());
            if (staffAttachmentEntity.getAttachment() != null) {
                attachment.setAttachmentId(staffAttachmentEntity.getAttachment().getAttachmentId());
                attachment.setReceiptDate(staffAttachmentEntity.getAttachment().getReceiptDate());
            }
            BeanUtils.copyProperties(staffAttachmentDto, staffAttachmentEntity);
        }
        if (file != null) {
            validateStaffAttachment(file);
            attachment.setAttachmentContents(file.getBytes());
            attachment.setAttachmentName(file.getOriginalFilename());
            attachment.setAttachmentStatus("ACTIVE");
            attachment.setDocumentType(file.getContentType());
            staffAttachmentEntity.setAttachment(attachment);
        }
        staffAttachmentEntity = staffAttachmentRepo.save(staffAttachmentEntity);
        staffAttachmentDto.setStaffAttachmentName(attachment.getAttachmentName());
        staffAttachmentDto.setStaffAttachmentType(attachment.getDocumentType());
        staffAttachmentDto.setStaffAttachmentFile(attachment.getAttachmentContents());
        staffAttachmentDto.setStaffAttachmentId(staffAttachmentEntity.getStaffAttachmentId());
        return staffAttachmentDto;
    }

    private void validateStaffAttachment(MultipartFile file) {
        boolean invalidStaffAttachment = true;

        if (file.getContentType().equals("image/png") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
                file.getContentType().equals("image/jpg") || file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("application/pdf") || file.getContentType().equals("application/vnd.ms-excel") ||
                file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                file.getContentType().equals("image/bmp") ||
                file.getContentType().equals("image/gif")) {
            invalidStaffAttachment = false;
        }
        if (invalidStaffAttachment) {
            throw new ResponseStatusException(
                    INTERNAL_SERVER_ERROR, " PNG DOCUMENT JPG PDF SHEET BMP AND GIF content type are allowed");
        }
    }

    private StaffAttachment readStaffAttachment(long staffImageId) {
        StaffAttachment StaffAttachment = staffAttachmentRepo.findById(staffImageId).filter(p -> p.getStatus().equals("ACTIVE"))
                .orElseThrow(() -> new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(staffImageId))));
        return StaffAttachment;
    }

    @Override
    public StaffAttachmentDto getOneFile(Long staffAttachmentId) {

        StaffAttachmentDto staffAttachmentDto = new StaffAttachmentDto();
        StaffAttachment staffAttachment = readStaffAttachment(staffAttachmentId);
        staffAttachmentDto.setStaffAttachmentId(staffAttachment.getStaffAttachmentId());
        staffAttachmentDto.setStaffId(staffAttachment.getStaffId());
        staffAttachmentDto.setType(staffAttachment.getType());
        staffAttachmentDto.setName(staffAttachment.getName());
        if (staffAttachment.getAttachment() != null) {
            staffAttachmentDto.setStaffAttachmentType(staffAttachment.getAttachment().getDocumentType());
            staffAttachmentDto.setStaffAttachmentFile(staffAttachment.getAttachment().getAttachmentContents());
            staffAttachmentDto.setStaffAttachmentName(staffAttachment.getAttachment().getAttachmentName());
        }
        return staffAttachmentDto;
    }

    @Override
    public List<StaffAttachmentDto> getAllFiles(Long staffId) {
        List<StaffAttachmentDto> staffAttachmentDtoList = new ArrayList<StaffAttachmentDto>();
        staffAttachmentDtoList = staffAttachmentRepo.findByStaffId(staffId)
                .stream()
                .map(attachment -> {
                    StaffAttachmentDto attachDto = new StaffAttachmentDto();
                    BeanUtils.copyProperties(attachment, attachDto);
                    if (attachment.getAttachment() != null) {
                        attachDto.setStaffAttachmentName(attachment.getAttachment().getAttachmentName());
                        attachDto.setStaffAttachmentType(attachment.getAttachment().getDocumentType());
                    }
                    return attachDto;
                }).collect(Collectors.toList());
        return staffAttachmentDtoList;
    }

    @Override
    public void removeStaffFile(Long staffAttachmentId) {
        StaffAttachment staffAttachment = readStaffAttachment(staffAttachmentId);
        staffAttachment.setStatus("INACTIVE");
        staffAttachmentRepo.save(staffAttachment);
    }
}