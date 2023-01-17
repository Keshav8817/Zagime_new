package org.cyfwms.familyhealing.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHProgressReportDto;
import org.cyfwms.familyhealing.dto.FHSafetyPlanDto;
import org.cyfwms.familyhealing.entity.FHProgressReport;
import org.cyfwms.familyhealing.entity.FHSafetyPlan;
import org.cyfwms.familyhealing.repository.FHProgressReportRepository;
import org.cyfwms.familyhealing.repository.FHSafetyPlanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FHProgressReportServiceImpl implements FHProgressReportService {
    @Autowired
    private FHProgressReportRepository fhProgressReportRepo;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public FHProgressReportDto saveProgressReport(FHProgressReportDto fhProgressReportDto) {
        log.info("Inside saveProgressReport");
        FHProgressReport fhProgressReport = null;
        if (fhProgressReportDto.getFhProgressReportId() == 0) {
            fhProgressReport = new FHProgressReport();
            BeanUtils.copyProperties(fhProgressReportDto, fhProgressReport);
            fhProgressReport.setStatus("ACTIVE");

        } else {
            fhProgressReport = fhProgressReportRepo.findById(fhProgressReportDto.getFhProgressReportId()).get();
            BeanUtils.copyProperties(fhProgressReportDto, fhProgressReport);
        }
        fhProgressReport = fhProgressReportRepo.save(fhProgressReport);
        fhProgressReportDto.setFhProgressReportId(fhProgressReport.getFhProgressReportId());
        fhProgressReportDto.setFhFileDetailsId(fhProgressReport.getFhFileDetailsId());
        log.info("Exit saveProgressReport");

        return fhProgressReportDto;
    }

    @Override
    public FHProgressReportDto readProgressReport(Long fhProgressReportId) {
        log.info("Inside readProgressReport");
        FHProgressReportDto fhCasePlanDto = new FHProgressReportDto();
        FHProgressReport fhSafetyPlan = fhProgressReportRepo.findById(
                fhProgressReportId).filter(active -> active.getStatus().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(fhProgressReportId))));
        BeanUtils.copyProperties(fhSafetyPlan, fhCasePlanDto);
        log.info("Exit readProgressReport");
        return fhCasePlanDto;
    }

    @Override
    public List<FHProgressReportDto> readAllProgressReport(Long fhFileDetailsId) {
        List<FHProgressReportDto> fhProgressReportDtoList = new ArrayList<FHProgressReportDto>();
        fhProgressReportDtoList = fhProgressReportRepo.findByFhFileDetailsId(fhFileDetailsId)
                .stream()
                .map(progressReport -> {
                    FHProgressReportDto fhProgressReportDto = new FHProgressReportDto();
                    BeanUtils.copyProperties(progressReport, fhProgressReportDto);
                    return fhProgressReportDto;
                }).collect(Collectors.toList());
        return fhProgressReportDtoList;
    }

    @Override
    public void removeProgressReport(Long fhProgressReportId) {
        log.info("Inside removeProgressReport");
        FHProgressReport fhProgressReport =
                fhProgressReportRepo.findById(fhProgressReportId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhProgressReportId))));

        if (fhProgressReport.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhProgressReportId)));
        }
        fhProgressReport.setStatus("INACTIVE");
        log.info("Exit removeProgressReport");
        fhProgressReportRepo.save(fhProgressReport);
    }
}
