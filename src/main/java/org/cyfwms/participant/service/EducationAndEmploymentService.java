package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.EducationAndEmploymentCompositeDto;

public interface EducationAndEmploymentService {
    EducationAndEmploymentCompositeDto readEducationAndEmployment(Long participantId);

    EducationAndEmploymentCompositeDto  saveEducationAndEmployment(EducationAndEmploymentCompositeDto educationAndEmploymentCompositeDto);
}
