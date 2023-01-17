package org.cyfwms.familyhealing.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHSafetyPlanDto;
import org.cyfwms.familyhealing.entity.FHSafetyPlan;
import org.cyfwms.familyhealing.repository.FHSafetyPlanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FHSafetyPlanServiceImpl implements FHSafetyPlanService {
    @Autowired
    private FHSafetyPlanRepository fhSafetyPlanRepo;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public FHSafetyPlanDto saveSafetyPlan(FHSafetyPlanDto fhSafetyPlanDto) {
        log.info("Inside saveSafetyPlan");
        FHSafetyPlan fhSafetyPlan = null;
        if (fhSafetyPlanDto.getFhSafetyPlanId() == 0) {
            fhSafetyPlan = new FHSafetyPlan();
            BeanUtils.copyProperties(fhSafetyPlanDto, fhSafetyPlan);
            fhSafetyPlan.setStatus("ACTIVE");

        } else {
            fhSafetyPlan = fhSafetyPlanRepo.findById(fhSafetyPlanDto.getFhSafetyPlanId()).get();
            BeanUtils.copyProperties(fhSafetyPlanDto, fhSafetyPlan);
        }
        fhSafetyPlan = fhSafetyPlanRepo.save(fhSafetyPlan);
        fhSafetyPlanDto.setFhSafetyPlanId(fhSafetyPlan.getFhSafetyPlanId());
        fhSafetyPlanDto.setFhFileDetailsId(fhSafetyPlan.getFhFileDetailsId());
        log.info("Exit saveSafetyPlan");

        return fhSafetyPlanDto;
    }

    @Override
    public FHSafetyPlanDto readSafetyPlan(Long fhSafetyPlanId) {
        log.info("Inside readSafetyPlan");
        FHSafetyPlanDto fhCasePlanDto = new FHSafetyPlanDto();
        FHSafetyPlan fhSafetyPlan = fhSafetyPlanRepo.findById(
                fhSafetyPlanId).filter(active -> active.getStatus().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(fhSafetyPlanId))));
        BeanUtils.copyProperties(fhSafetyPlan, fhCasePlanDto);
        log.info("Exit readSafetyPlan");
        return fhCasePlanDto;
    }

    @Override
    public List<FHSafetyPlanDto> readAllSafetyPlan(Long fhFileDetailsId) {
        List<FHSafetyPlanDto> fhSafetyPlanDtoList = new ArrayList<FHSafetyPlanDto>();
        fhSafetyPlanDtoList = fhSafetyPlanRepo.findByFhFileDetailsId(fhFileDetailsId)
                .stream()
                .map(safetyPlan -> {
                    FHSafetyPlanDto fhCasePlanDto = new FHSafetyPlanDto();
                    BeanUtils.copyProperties(safetyPlan, fhCasePlanDto);
                    return fhCasePlanDto;
                }).collect(Collectors.toList());
        return fhSafetyPlanDtoList;
    }

    @Override
    public void removeSafetyPlan(Long fhCasePlanId) {
        log.info("Inside removeSafetyPlan");
        FHSafetyPlan fhSafetyPlan =
                fhSafetyPlanRepo.findById(fhCasePlanId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhCasePlanId))));

        if (fhSafetyPlan.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhCasePlanId)));
        }
        fhSafetyPlan.setStatus("INACTIVE");
        log.info("Exit removeSafetyPlan");
        fhSafetyPlanRepo.save(fhSafetyPlan);
    }
}
