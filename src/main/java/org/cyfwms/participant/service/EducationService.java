package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.EducationDto;

import java.util.Optional;

public interface EducationService {
    EducationDto readEducation(Long participantId);

    EducationDto saveEducation(EducationDto educationDto);

}
