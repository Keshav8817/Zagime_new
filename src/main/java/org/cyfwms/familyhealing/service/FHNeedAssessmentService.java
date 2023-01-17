package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHNeedAssessmentDto;

import java.util.List;

public interface FHNeedAssessmentService {
    FHNeedAssessmentDto saveNeedAssessment(FHNeedAssessmentDto fhNeedAssessmentDto);

    FHNeedAssessmentDto readNeedAssessment(Long fhNeedAssessmentId);

    List<FHNeedAssessmentDto> readAllNeedAssessment(Long fhFileDetailsId);

    void removeNeedAssessment(Long fhNeedAssessmentId);
}
