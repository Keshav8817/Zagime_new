package org.cyfwms.culturalprogram.service;
import org.cyfwms.culturalprogram.dto.CPASearchDto;
import org.cyfwms.culturalprogram.dto.CPASearchResultsDto;
import org.cyfwms.culturalprogram.repository.CPASearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CPASearchService {
    @Autowired
    CPASearchRepository CPASearchRepository;
    public List<CPASearchResultsDto> searchCulturalProgAndAct(CPASearchDto CPASearchDto) {
        return CPASearchRepository.searchCulturalProgAndAct(CPASearchDto);
    }
}
