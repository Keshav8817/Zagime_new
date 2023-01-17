package org.cyfwms.culturalprogram.service;
import org.cyfwms.culturalprogram.dto.CPASearchCriteriaDto;
import org.cyfwms.culturalprogram.dto.CPAParticipantSearchResultsDto;
import org.cyfwms.culturalprogram.repository.CPAParticipantSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CPAParticipantSearchService {
    @Autowired
    CPAParticipantSearchRepository CPAParticipantSearchRepository;
    public List<CPAParticipantSearchResultsDto> searchParticipantCulturalProgAndAct(CPASearchCriteriaDto participantCulturalSearchDto) {
            return CPAParticipantSearchRepository.searchParticipantCulturalProgAndAct(participantCulturalSearchDto);
        }
}
