package org.cyfwms.familyhealing.service;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHCasePlanDto;
import org.cyfwms.familyhealing.dto.FHNeedAssessmentDto;
import org.cyfwms.familyhealing.entity.FHCasePlan;
import org.cyfwms.familyhealing.entity.FHNeedAssessment;
import org.cyfwms.familyhealing.repository.FHCasePlanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FHCasePlanServiceImpl implements FHCasePlanService{
    @Autowired
    private FHCasePlanRepository fhCasePlanRepo;
    @Autowired
    private MessageUtil messageUtil;
    @Override
    public FHCasePlanDto saveCasePlan(FHCasePlanDto fhCasePlanDto) {
        log.info("Inside saveCasePlan");
        FHCasePlan fhNeedAssessment = null;
        if (fhCasePlanDto.getFhCasePlanId() == 0) {
            fhNeedAssessment = new FHCasePlan();
            BeanUtils.copyProperties(fhCasePlanDto, fhNeedAssessment);
            fhNeedAssessment.setStatus("ACTIVE");

        } else {
            fhNeedAssessment = fhCasePlanRepo.findById(fhCasePlanDto.getFhCasePlanId()).get();
            BeanUtils.copyProperties(fhCasePlanDto, fhNeedAssessment);
        }
        fhNeedAssessment = fhCasePlanRepo.save(fhNeedAssessment);
        fhCasePlanDto.setFhCasePlanId(fhNeedAssessment.getFhCasePlanId());
        fhCasePlanDto.setFhFileDetailsId(fhNeedAssessment.getFhFileDetailsId());
        log.info("Exit SaveCasePlan");

        return fhCasePlanDto;
    }

    @Override
    public FHCasePlanDto readCasePlan(Long fhCasePlanId) {
        log.info("Inside readCasePlan");
        FHCasePlanDto fhCasePlanDto = new FHCasePlanDto();
        FHCasePlan fhCasePlan = fhCasePlanRepo.findById(
                fhCasePlanId).filter(active -> active.getStatus().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(fhCasePlanId))));
        BeanUtils.copyProperties(fhCasePlan, fhCasePlanDto);
        log.info("Exit ReadCasePlan");
        return fhCasePlanDto;
    }

    @Override
    public List<FHCasePlanDto> readAllCasePlan(Long fhFileDetailsId) {
        List<FHCasePlanDto> fhCasePlanDtoList = new ArrayList<FHCasePlanDto>();
        fhCasePlanDtoList = fhCasePlanRepo.findByFhFileDetailsId(fhFileDetailsId)
                .stream()
                .map(casePlan -> {
                    FHCasePlanDto fhCasePlanDto = new FHCasePlanDto();
                    BeanUtils.copyProperties(casePlan, fhCasePlanDto);
                    return fhCasePlanDto;
                }).collect(Collectors.toList());
        return fhCasePlanDtoList;
    }

    @Override
    public void removeCasePlan(Long fhCasePlanId) {
        log.info("Inside RemoveCasePlane");
        FHCasePlan fhCasePlan =
                fhCasePlanRepo.findById(fhCasePlanId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhCasePlanId))));

        if (fhCasePlan.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhCasePlanId)));
        }
        fhCasePlan.setStatus("INACTIVE");
        log.info("Exit RemoveCasePlane");
        fhCasePlanRepo.save(fhCasePlan);
    }
}
