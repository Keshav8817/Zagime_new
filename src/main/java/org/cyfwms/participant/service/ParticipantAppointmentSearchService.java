package org.cyfwms.participant.service;
import org.cyfwms.participant.dto.ParticipantAppointmentSearchCriteriaDto;
import org.cyfwms.participant.dto.ParticipantAppointmentSearchResultsDto;
import org.cyfwms.participant.repository.ParticipantAppointmentSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipantAppointmentSearchService {
    @Autowired
    ParticipantAppointmentSearchRepository participantAppointmentSearchRepository;
    public List<ParticipantAppointmentSearchResultsDto> search(ParticipantAppointmentSearchCriteriaDto participantAppointmentSearchCriteriaDto) {
        return participantAppointmentSearchRepository.searchParticipantContactNotes(participantAppointmentSearchCriteriaDto);
    }
}
