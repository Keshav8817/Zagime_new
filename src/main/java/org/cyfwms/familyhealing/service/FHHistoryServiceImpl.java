package org.cyfwms.familyhealing.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHHistoryDto;
import org.cyfwms.familyhealing.entity.FHHistory;
import org.cyfwms.familyhealing.entity.FHReferral;
import org.cyfwms.familyhealing.repository.FHHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FHHistoryServiceImpl implements FHHistoryService {
    @Autowired
    private FHHistoryRepository fHHistoryRepository;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public FHHistoryDto readHistory(Long fhFileDetailsId) {
        log.info("Inside ReadFHHistory");
        FHHistoryDto FHHistoryDto = new FHHistoryDto();
        if (fhFileDetailsId != 0) {
            Optional<FHHistory> fHReferral = Optional.ofNullable(fHHistoryRepository.findByFhFileDetailsId(fhFileDetailsId).orElseThrow(() ->
                    new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhFileDetailsId)))));

            BeanUtils.copyProperties(fHReferral.get(), FHHistoryDto);
            log.info("Exit ReadFHHistory");
        }
        return FHHistoryDto;
    }

    @Override
    public FHHistoryDto saveHistory(FHHistoryDto fhHistoryDto) {
        log.info("Inside SaveFHHistory");
        FHHistory fHHistory = null;
        if (fhHistoryDto.getFhHistoryId() == 0) {
            fHHistory = new FHHistory();
            BeanUtils.copyProperties(fhHistoryDto, fHHistory);
            fHHistory.setStatus("ACTIVE");

        } else {
            fHHistory = fHHistoryRepository.findById(fhHistoryDto.getFhHistoryId()).get();
            BeanUtils.copyProperties(fhHistoryDto, fHHistory);
        }
        fHHistory = fHHistoryRepository.save(fHHistory);
        fhHistoryDto.setFhFileDetailsId(fHHistory.getFhFileDetailsId());
        fhHistoryDto.setFhHistoryId(fHHistory.getFhHistoryId());
        log.info("Exit SaveFHHistory");
        return fhHistoryDto;
    }
}
