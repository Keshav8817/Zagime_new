package org.cyfwms.participant.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.CounselorCFSWorkersDto;
import org.cyfwms.participant.entity.CounselorCFSWorker;
import org.cyfwms.participant.repository.CounselorCFSWorkerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CounselorCFSWorkerServiceImpl implements CounselorCFSWorkerService{
    @Autowired
    CounselorCFSWorkerRepository cfsWorkerRepository;

    @Override
    public List<CounselorCFSWorkersDto> getAllCounselorCFSWorkers(Long participantId) {
        log.info("Inside GetAllCounselorCFSWorkers");
        List<CounselorCFSWorkersDto> counselorCFSWorkersDtoList = new ArrayList<CounselorCFSWorkersDto>();
        if (participantId != 0) {
            counselorCFSWorkersDtoList = cfsWorkerRepository.findByParticipantId(participantId)
                    .stream()
                    .map(counselorCFSWorker -> {
                        CounselorCFSWorkersDto ccWorkerDto = new CounselorCFSWorkersDto();
                        BeanUtils.copyProperties(counselorCFSWorker, ccWorkerDto);
                        return ccWorkerDto;
                    }).collect(Collectors.toList());
        }
        log.info("Exit GetAllCounselorCFSWorkers");
        return counselorCFSWorkersDtoList;
    }

    @Override
    public List<CounselorCFSWorkersDto> saveAllCounselorCFSWorkers(List<CounselorCFSWorkersDto> counselorCFSWorkersDtoList) {
        log.info("Inside SaveAllCounselorCFSWorkers");
        for (CounselorCFSWorkersDto CounselorCFSWorkersDto: counselorCFSWorkersDtoList) {
            CounselorCFSWorker counselorCFSWorker = null;
            if (CounselorCFSWorkersDto.getCounselorCFSWorkerId() == 0) {
                counselorCFSWorker = new CounselorCFSWorker();
                BeanUtils.copyProperties(CounselorCFSWorkersDto, counselorCFSWorker);
                counselorCFSWorker.setStatus("ACTIVE");
            } else {
                counselorCFSWorker = cfsWorkerRepository.findById(CounselorCFSWorkersDto.getCounselorCFSWorkerId()).get();
                BeanUtils.copyProperties(CounselorCFSWorkersDto, counselorCFSWorker);
            }
            counselorCFSWorker = cfsWorkerRepository.save(counselorCFSWorker);
            CounselorCFSWorkersDto.setCounselorCFSWorkerId(counselorCFSWorker.getCounselorCFSWorkerId());
        }
        log.info("Exit SaveAllCounselorCFSWorkers");
        return counselorCFSWorkersDtoList;
    }

    @Override
    public List<CounselorCFSWorkersDto> removeCounselorCFSWorker(Long counselorCFSWorkerId) {
        log.info("Inside RemoveCounselorCFSWorkers");
        Optional<CounselorCFSWorker> counselorCFSWorkerOpt =
                cfsWorkerRepository.findById(counselorCFSWorkerId);
        Long participantId = 0l;
        if(counselorCFSWorkerOpt.isPresent()){
            CounselorCFSWorker counselorCFSWorker = counselorCFSWorkerOpt.get();
            participantId = counselorCFSWorkerOpt.get().getParticipantId();
            counselorCFSWorker.setStatus("INACTIVE");

            cfsWorkerRepository.save(counselorCFSWorker);
        }
        List<CounselorCFSWorkersDto> cs = getAllCounselorCFSWorkers(participantId);
        log.info("Exit RemoveCounselorCFSWorkers");
        return cs;

    }
}
