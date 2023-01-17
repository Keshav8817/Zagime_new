package org.cyfwms.familyhealing.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.entity.Attachment;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHConsentFormsDto;
import org.cyfwms.familyhealing.entity.FHConsentForms;
import org.cyfwms.familyhealing.repository.FHConsentFormsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FHConsentFormsServiceImpl implements FHConsentFormsService {
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private FHConsentFormsRepo fhConsentFormsRepo;

    @Override
    public FHConsentFormsDto saveConsentForms(FHConsentFormsDto fhConsentFormsDto, MultipartFile multipartFile) throws IOException {
        log.info("Inside Upload FamilyHealing ConsentForms");
        Attachment attachment = null;
        FHConsentForms fHConsentForms = new FHConsentForms();
        attachment = new Attachment();
        long fhConsentFormsId = fhConsentFormsDto.getFhConsentFormsId();
        if (fhConsentFormsDto.getFhConsentFormsId() == 0) {
            fHConsentForms.setStatus("ACTIVE");
            BeanUtils.copyProperties(fhConsentFormsDto, fHConsentForms);
        } else {
            fHConsentForms = readConsent(fhConsentFormsId);
            fhConsentFormsDto.setFhConsentFormsId(fhConsentFormsDto.getFhConsentFormsId());
            if (fHConsentForms.getAttachment() != null) {
                attachment.setAttachmentId(fHConsentForms.getAttachment().getAttachmentId());
                attachment.setReceiptDate(fHConsentForms.getAttachment().getReceiptDate());
            }
            BeanUtils.copyProperties(fhConsentFormsDto, fHConsentForms);
        }

        if (multipartFile != null) {
            validateConsentForm(multipartFile);
            attachment.setAttachmentContents(multipartFile.getBytes());
            attachment.setAttachmentName(multipartFile.getOriginalFilename());
            attachment.setAttachmentStatus("ACTIVE");
            attachment.setDocumentType(multipartFile.getContentType());
            fHConsentForms.setAttachment(attachment);
        }
        fHConsentForms = fhConsentFormsRepo.save(fHConsentForms);
        fhConsentFormsDto.setFhAttachmentName(attachment.getAttachmentName());
        fhConsentFormsDto.setFhAttachmentType(attachment.getDocumentType());
        fhConsentFormsDto.setFile(attachment.getAttachmentContents());
        fhConsentFormsDto.setFhConsentFormsId(fHConsentForms.getFhConsentFormsId());
        log.info("Exit FamilyHealing ConsentForms");
        return fhConsentFormsDto;


    }

    private void validateConsentForm(MultipartFile multipartFile) {
        log.info("Inside Validate FamilyHealing ConsentForm");
        boolean invalidFhAttachment = true;
        if (multipartFile.getContentType().equals("image/png") ||
                multipartFile.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
                multipartFile.getContentType().equals("image/jpg") || multipartFile.getContentType().equals("image/jpeg") ||
                multipartFile.getContentType().equals("application/pdf") || multipartFile.getContentType().equals("application/vnd.ms-excel") ||
                multipartFile.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                multipartFile.getContentType().equals("image/bmp") ||
                multipartFile.getContentType().equals("image/gif")) {
            invalidFhAttachment = false;
        }
        if (invalidFhAttachment) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, " PNG DOCUMENT JPG PDF SHEET BMP AND GIF content type are allowed");
        }
        log.info("Exit ValidateFHConsentForms");


    }

    private FHConsentForms readConsent(long fhConsentFormsId) {
        log.info("Inside Read FamilyHealing Consent Forms");
        FHConsentForms fHConsentForms = fhConsentFormsRepo.findById(fhConsentFormsId).filter(p -> p.getStatus().equals("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(fhConsentFormsId))));
        log.info("Exit Read FamilyHealing Consent Forms");
        return fHConsentForms;
    }

    @Override
    public FHConsentFormsDto readConsentForms(Long fhConsentFormsId) {
        log.info("Inside GetOneFile InitialContactAttachment");
        FHConsentFormsDto fHConsentFormsDto = new FHConsentFormsDto();
        FHConsentForms fHConsentForms = readConsent(fhConsentFormsId);
        BeanUtils.copyProperties(fHConsentForms, fHConsentFormsDto);
        fHConsentFormsDto.setFhConsentFormsId(fHConsentForms.getFhConsentFormsId());
        fHConsentFormsDto.setFhFileDetailsId(fHConsentForms.getFhFileDetailsId());
        fHConsentFormsDto.setType(fHConsentForms.getType());
        if (fHConsentForms.getAttachment() != null) {
            fHConsentFormsDto.setFhAttachmentType(fHConsentForms.getAttachment().getDocumentType());
            fHConsentFormsDto.setFile(fHConsentForms.getAttachment().getAttachmentContents());
            fHConsentFormsDto.setFhAttachmentName(fHConsentForms.getAttachment().getAttachmentName());
        }
        log.info("Exit GetOneFile InitialContactAttachment");
        return fHConsentFormsDto;
    }

    @Override
    public List<FHConsentFormsDto> readAllConsentForms(Long fhFileDetailsId) {
        List<FHConsentFormsDto> fhConsentFormsDtoList = new ArrayList<FHConsentFormsDto>();
        fhConsentFormsDtoList = fhConsentFormsRepo.findByFhFileDetailsId(fhFileDetailsId)
                .stream()
                .map(consentForms -> {
                    FHConsentFormsDto fhConsentFormsDto = new FHConsentFormsDto();
                    BeanUtils.copyProperties(consentForms, fhConsentFormsDto);
                    if (consentForms.getAttachment() != null) {
                        fhConsentFormsDto.setFhAttachmentName(consentForms.getAttachment().getAttachmentName());
                        fhConsentFormsDto.setFhAttachmentType(consentForms.getAttachment().getDocumentType());
                    }
                    return fhConsentFormsDto;
                }).collect(Collectors.toList());
        return fhConsentFormsDtoList;
    }

    @Override
    public void removeConsentForms(Long fhConsentFormsId) {
        log.info("Inside removeConsentForms");
        FHConsentForms fhConsentForms =
                fhConsentFormsRepo.findById(fhConsentFormsId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhConsentFormsId))));

        if (fhConsentForms.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhConsentFormsId)));
        }
        fhConsentForms.setStatus("INACTIVE");
        log.info("Exit removeConsentForms");
        fhConsentFormsRepo.save(fhConsentForms);

    }
}
