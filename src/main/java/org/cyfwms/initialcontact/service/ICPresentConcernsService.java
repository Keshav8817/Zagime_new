package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICPresentConcernsDto;

public interface ICPresentConcernsService {
    ICPresentConcernsDto readPresentConcerns(Long fileDetailsId);
    ICPresentConcernsDto savePresentConcerns(ICPresentConcernsDto initialContactPresentConcernsDto);
}
