package org.cyfwms.participant.service;
import org.cyfwms.participant.dto.CounselorCFSWorkersDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CounselorCFSWorkerService {
    List<CounselorCFSWorkersDto> getAllCounselorCFSWorkers(Long participantId);
    List<CounselorCFSWorkersDto> saveAllCounselorCFSWorkers(List<CounselorCFSWorkersDto> CounselorCFSWorkersDtoList);
    List<CounselorCFSWorkersDto> removeCounselorCFSWorker(Long counselorCFSWorkerId);
}
