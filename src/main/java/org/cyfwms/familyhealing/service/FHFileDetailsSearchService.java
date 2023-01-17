package org.cyfwms.familyhealing.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.familyhealing.dto.FHFileDetailsDto;
import org.cyfwms.familyhealing.dto.FHFileDetailsSearchCriteriaDto;
import org.cyfwms.familyhealing.dto.FHFileDetailsSearchResultDto;
import org.cyfwms.familyhealing.repository.FHFileDetailsSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class FHFileDetailsSearchService {
    @Autowired
    private FHFileDetailsSearchRepository fileDetailsSearchRepository;
    public List<FHFileDetailsSearchResultDto> searchFileDetails(FHFileDetailsSearchCriteriaDto fileDetailsSearchCriteriaDto) {
        log.info("FHFileDetailsSearchCriteriaDto :"+fileDetailsSearchCriteriaDto);
        return fileDetailsSearchRepository.searchFileDetails(fileDetailsSearchCriteriaDto);
    }
}
