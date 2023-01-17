package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICFileDetailsDto;
import org.springframework.http.ResponseEntity;

public interface ICFileDetailsService {
    ICFileDetailsDto readAllFileDetails(Long fileDetailsID );
    ICFileDetailsDto saveAllFileDetails(ICFileDetailsDto initialContactFileDetailsDto);
    void removeICFileDetails(Long fileDetailsId);
}
