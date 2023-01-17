package org.cyfwms.culturalprogram.service;
import org.cyfwms.culturalprogram.dto.CPAParticipantDto;
import org.springframework.http.ResponseEntity;
public interface CPAParticipantService {
    CPAParticipantDto saveCpaParticipant(CPAParticipantDto CPAParticipantDto);
    CPAParticipantDto readCpaParticipant(Long culturalProgramId);
    void removeCpaParticipant(Long participantCulturalProId);
}
