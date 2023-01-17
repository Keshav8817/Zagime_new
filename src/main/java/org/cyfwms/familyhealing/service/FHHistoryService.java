package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHHistoryDto;

public interface FHHistoryService {
    FHHistoryDto readHistory(Long fhFileDetailsId);

    FHHistoryDto saveHistory(FHHistoryDto fhHistoryDto);
}
