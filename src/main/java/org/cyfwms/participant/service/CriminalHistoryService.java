package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.CounselorCFSWorkersDto;
import org.cyfwms.participant.dto.CriminalHistoryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CriminalHistoryService {
    CriminalHistoryDto readCriminalHistory(Long participantId);
    CriminalHistoryDto saveCriminalHistory(CriminalHistoryDto criminalHistoryDto);
    CriminalHistoryDto removeCriminalHistoryRecord(Long criminalHistoryRecordId);
}
