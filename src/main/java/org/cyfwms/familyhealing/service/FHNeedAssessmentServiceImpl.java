package org.cyfwms.familyhealing.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHNeedAssessmentDto;
import org.cyfwms.familyhealing.entity.FHNeedAssessment;
import org.cyfwms.familyhealing.repository.FHNeedAssessmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FHNeedAssessmentServiceImpl implements FHNeedAssessmentService{

    @Autowired
    private FHNeedAssessmentRepository fHNeedAssessmentRepo;
    @Autowired
    private MessageUtil messageUtil;
    @Override
    public FHNeedAssessmentDto saveNeedAssessment(FHNeedAssessmentDto fhNeedAssessmentDto) {
        log.info("Inside saveNeedAssessment");
        FHNeedAssessment fhNeedAssessment = null;
        if (fhNeedAssessmentDto.getFhNeedAssessmentId() == 0) {
            fhNeedAssessment = new FHNeedAssessment();
            BeanUtils.copyProperties(fhNeedAssessmentDto, fhNeedAssessment);
            fhNeedAssessment.setStatus("ACTIVE");

        } else {
            fhNeedAssessment = fHNeedAssessmentRepo.findById(fhNeedAssessmentDto.getFhNeedAssessmentId()).get();
            BeanUtils.copyProperties(fhNeedAssessmentDto, fhNeedAssessment);
        }
        fhNeedAssessment = fHNeedAssessmentRepo.save(fhNeedAssessment);
        fhNeedAssessmentDto.setFhNeedAssessmentId(fhNeedAssessment.getFhNeedAssessmentId());
        fhNeedAssessmentDto.setFhFileDetailsId(fhNeedAssessment.getFhFileDetailsId());
        log.info("Exit SaveNeedAssessment");

        return fhNeedAssessmentDto;
    }

    @Override
    public FHNeedAssessmentDto readNeedAssessment(Long fhNeedAssessmentId) {
        log.info("Inside readNeedAssessment");
        FHNeedAssessmentDto fhNeedAssessmentDto = new FHNeedAssessmentDto();
        FHNeedAssessment fhNeedAssessment = fHNeedAssessmentRepo.findById(
                fhNeedAssessmentId).filter(active -> active.getStatus().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(fhNeedAssessmentId))));
        BeanUtils.copyProperties(fhNeedAssessment, fhNeedAssessmentDto);
        log.info("Exit ReadNeedAssessment");
        return fhNeedAssessmentDto;
    }

    @Override
    public List<FHNeedAssessmentDto> readAllNeedAssessment(Long fhFileDetailsId) {
        List<FHNeedAssessmentDto> fHNeedAssessmentDto = new ArrayList<FHNeedAssessmentDto>();
        fHNeedAssessmentDto = fHNeedAssessmentRepo.findByFhFileDetailsId(fhFileDetailsId)
                .stream()
                .map(backgroundCheck -> {
                    FHNeedAssessmentDto backgroundCheckDto = new FHNeedAssessmentDto();
                    BeanUtils.copyProperties(backgroundCheck, backgroundCheckDto);
                    return backgroundCheckDto;
                }).collect(Collectors.toList());
        return fHNeedAssessmentDto;
    }

    @Override
    public void removeNeedAssessment(Long fhNeedAssessmentId) {
        log.info("Inside RemoveNeedAssessment");
        FHNeedAssessment fhNeedAssessment =
                fHNeedAssessmentRepo.findById(fhNeedAssessmentId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhNeedAssessmentId))));

        if (fhNeedAssessment.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhNeedAssessmentId)));
        }
        fhNeedAssessment.setStatus("INACTIVE");
        log.info("Exit RemoveNeedAssessment");
        fHNeedAssessmentRepo.save(fhNeedAssessment);
    }
}
