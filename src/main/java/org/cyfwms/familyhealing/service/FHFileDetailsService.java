package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHFileDetailsDto;

import java.util.List;

public interface FHFileDetailsService {
  

    FHFileDetailsDto saveFileDetails(FHFileDetailsDto fhFileDetailsDto);

    void removeFileDetails(Long fhDetailsId);

    FHFileDetailsDto readFileDetails(Long fhFileDetailsId);

}
