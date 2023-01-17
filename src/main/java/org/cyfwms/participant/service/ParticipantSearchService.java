package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.ParticipantSearchCriteriaDto;
import org.cyfwms.participant.dto.ParticipantSearchResultsDto;
import org.cyfwms.participant.repository.ParticipantSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantSearchService {
    List<ParticipantSearchResultsDto> search(ParticipantSearchCriteriaDto participantSearchCriteriaDto);
}
