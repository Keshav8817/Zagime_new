package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.EmploymentDto;

public interface EmploymentService {
    EmploymentDto readEmployment(Long participantId);

    EmploymentDto saveEmployment(EmploymentDto employmentDto);
}
