package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHConsentFormsDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FHConsentFormsService {
    FHConsentFormsDto saveConsentForms(FHConsentFormsDto fhConsentFormsDto, MultipartFile multipartFile) throws IOException;

    FHConsentFormsDto readConsentForms(Long fhConsentFormsId);

    List<FHConsentFormsDto> readAllConsentForms(Long fhFileDetailsId);

    void removeConsentForms(Long fhConsentFormsId);
}
