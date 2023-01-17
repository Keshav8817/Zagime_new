package org.cyfwms.participant.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.CriminalHistoryDto;
import org.cyfwms.participant.dto.CriminalHistoryRecordDto;
import org.cyfwms.participant.entity.CriminalHistory;
import org.cyfwms.participant.entity.CriminalHistoryRecord;
import org.cyfwms.participant.repository.CriminalHistoryRecordRepository;
import org.cyfwms.participant.repository.CriminalHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CriminalHistoryServiceImpl implements CriminalHistoryService{
    @Autowired
    private CriminalHistoryRepository criminalHistoryRepo;

    @Autowired
    CriminalHistoryRecordRepository cHistoryRecordRepo;

    @Override
    public CriminalHistoryDto readCriminalHistory(Long participantId) {
        log.info("Inside ReadCriminalHistory");
        CriminalHistoryDto criminalHistoryDto = new CriminalHistoryDto();
        List<CriminalHistoryRecordDto> criminalHistoryRecordDtoList = null;
        if (participantId != 0) {
            //Criminal History
            Optional<CriminalHistory> criminalHistoryOpt = Optional.ofNullable(criminalHistoryRepo.findByParticipantId(participantId));
            if (criminalHistoryOpt.isPresent()) {
                CriminalHistory criminalHistory = criminalHistoryOpt.get();
                List<CriminalHistoryRecord> criminalHistoryRecordList =
                        criminalHistory
                                .getCriminalHistoryRecordList()
                                .stream()
                                .filter(chRecord ->
                                        chRecord.getStatus().equalsIgnoreCase("ACTIVE")
                                )
                                .map(cHistoryRecord -> {
                                    return cHistoryRecord;
                                })
                                .collect(Collectors.toList());
                criminalHistory.setCriminalHistoryRecordList(criminalHistoryRecordList);
                BeanUtils.copyProperties(criminalHistory, criminalHistoryDto);

                criminalHistoryRecordDtoList=criminalHistory.getCriminalHistoryRecordList().stream().map(chRecordDto->{
                    CriminalHistoryRecordDto criminalHistoryRecordDto =
                            new CriminalHistoryRecordDto();
                    BeanUtils.copyProperties(chRecordDto,criminalHistoryRecordDto);
                    return criminalHistoryRecordDto;
                }).collect(Collectors.toList());
                criminalHistoryDto.setCriminalHistoryRecordList(criminalHistoryRecordDtoList);
            }
        }
        log.info("Exit ReadCriminalHistory");
        return criminalHistoryDto;
    }

    @Override
    public CriminalHistoryDto saveCriminalHistory(CriminalHistoryDto criminalHistoryDto) {
        log.info("Inside SaveCriminalHistory");
        CriminalHistory criminalHistory = null;
        List<CriminalHistoryRecord> criminalHistoryRecordList = null;
        if (criminalHistoryDto.getCriminalHistoryId() == 0) {
            criminalHistory = new CriminalHistory();
            BeanUtils.copyProperties(criminalHistoryDto, criminalHistory);
            criminalHistory.setStatus("ACTIVE");

            criminalHistoryRecordList =
                    criminalHistoryDto.getCriminalHistoryRecordList()
                    .stream()
                    .map(cHistoryRecordDto -> {
                        CriminalHistoryRecord criminalHistoryRecord =
                                new CriminalHistoryRecord();
                        BeanUtils.copyProperties(cHistoryRecordDto, criminalHistoryRecord);
                        criminalHistoryRecord.setStatus("ACTIVE");
                        return criminalHistoryRecord;
                    })
                    .collect(Collectors.toList());
        } else {
            criminalHistory = criminalHistoryRepo.findById(criminalHistoryDto.getCriminalHistoryId()).get();
            criminalHistory.setConditions(criminalHistoryDto.getConditions());
            criminalHistory.setCourtWorkerAndContactInfo(criminalHistoryDto.getCourtWorkerAndContactInfo());

            //Received from screen
            criminalHistoryRecordList =
                    criminalHistoryDto.getCriminalHistoryRecordList()
                            .stream()
                            .map(cHistoryRecordDto -> {
                                CriminalHistoryRecord criminalHistoryRecord =
                                        new CriminalHistoryRecord();
                                BeanUtils.copyProperties(cHistoryRecordDto, criminalHistoryRecord);
                                criminalHistoryRecord.setStatus("ACTIVE");
                                return criminalHistoryRecord;
                            })
                            .collect(Collectors.toList());
        }
        criminalHistory.setCriminalHistoryRecordList(criminalHistoryRecordList);
        criminalHistory = criminalHistoryRepo.save(criminalHistory);
        criminalHistoryDto.setCriminalHistoryId(criminalHistory.getCriminalHistoryId());
        log.info("Exit SaveCriminalHistory");
        return criminalHistoryDto;
    }



    @Override
    public CriminalHistoryDto removeCriminalHistoryRecord(Long criminalHistoryRecordId) {
        log.info("Inside RemoveCriminalHistoryRecord");
        Long id=0l;
        Long participantId=0l;
        Optional<CriminalHistoryRecord> criminalHistoryRecordOpt =
                cHistoryRecordRepo.findById(criminalHistoryRecordId);
        if(criminalHistoryRecordOpt.isPresent()){
            CriminalHistoryRecord criminalHistoryRecord = criminalHistoryRecordOpt.get();
            id = criminalHistoryRecordOpt.get().getCriminalHistoryId();
            criminalHistoryRecord.setStatus("INACTIVE");
            cHistoryRecordRepo.save(criminalHistoryRecord);
        }
        Optional<CriminalHistory> ch = criminalHistoryRepo.findById(id);
        if(ch.isPresent())
        {
            participantId = ch.get().getParticipantId();
        }
        CriminalHistoryDto criminalHistoryDto = readCriminalHistory(participantId);
        log.info("Exit remove criminal history record");
        return criminalHistoryDto;
    }
}
