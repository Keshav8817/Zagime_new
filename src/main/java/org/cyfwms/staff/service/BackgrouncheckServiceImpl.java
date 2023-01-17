package org.cyfwms.staff.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.BackgroundCheckDto;
import org.cyfwms.staff.entity.BackgroundCheck;
import org.cyfwms.staff.entity.Staff;
import org.cyfwms.staff.repository.BackgoundCheckRepo;
import org.cyfwms.staff.repository.StaffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class BackgrouncheckServiceImpl implements BackgroundCheckService{
    @Autowired
    BackgoundCheckRepo backgoundCheckRepo;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private StaffRepository staffRepository;
    @Override
    public BackgroundCheckDto saveBackgroundCheck(BackgroundCheckDto backgroundCheckDto) {
        log.info("Inside saveBackgroundCheck");
        BackgroundCheck backgroundCheck = null;
        if (backgroundCheckDto.getBackgrounCheckId() == 0) {
            backgroundCheck = new BackgroundCheck();
            BeanUtils.copyProperties(backgroundCheckDto, backgroundCheck);
            backgroundCheck.setStatusOfDeletion("ACTIVE");

        } else {
            backgroundCheck = backgoundCheckRepo.findById(backgroundCheckDto.getBackgrounCheckId()).get();
            BeanUtils.copyProperties(backgroundCheckDto, backgroundCheck);
        }
        backgroundCheck = backgoundCheckRepo.save(backgroundCheck);
        backgroundCheckDto.setBackgrounCheckId(backgroundCheck.getBackgrounCheckId());
        log.info("Exit SaveStaffTraining");

        return backgroundCheckDto;
    }

    @Override
    public BackgroundCheckDto readBackgroundCheck(Long BackgrounCheckId) {
        log.info("Inside readBackgroundCheck");
        BackgroundCheckDto backgroundCheckDto = new BackgroundCheckDto();
        BackgroundCheck backgroundCheck = backgoundCheckRepo.findById(
                BackgrounCheckId).filter(active -> active.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(BackgrounCheckId))));
        BeanUtils.copyProperties(backgroundCheck, backgroundCheckDto);
        if (backgroundCheck.getRequestedBy()!=null) {
            if (!backgroundCheck.getRequestedBy().equals("0")) {
                Staff participant = staffRepository.findByStaffId(Long.parseLong(backgroundCheckDto.getRequestedBy()));
                backgroundCheckDto.setRequestedBy(participant.getFirstName() + " " + participant.getLastName());
                backgroundCheckDto.setRequestedById(participant.getStaffId());
            }
        }
        log.info("Exit ReadStaffTraining");
        return backgroundCheckDto;
    }

    @Override
    public void deleteBackgroundCheck(Long BackgroundCheckId) {
        log.info("Inside DeleteBackgroundCheck");
        BackgroundCheck backgroundCheck =
                backgoundCheckRepo.findById(BackgroundCheckId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(BackgroundCheckId))));

        if (backgroundCheck.getStatusOfDeletion().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(BackgroundCheckId)));
        }
        backgroundCheck.setStatusOfDeletion("INACTIVE");
        log.info("Exit RemoveStaff");
        backgoundCheckRepo.save(backgroundCheck);
    }

    @Override
    public List<BackgroundCheckDto> readAllBackgroundCheck(Long staffId) {
        List<BackgroundCheckDto> backgroundCheckDtos = new ArrayList<BackgroundCheckDto>();
        backgroundCheckDtos = backgoundCheckRepo.findByStaffId(staffId)
                .stream()
                .map(backgroundCheck -> {
                    BackgroundCheckDto backgroundCheckDto = new BackgroundCheckDto();
                    BeanUtils.copyProperties(backgroundCheck, backgroundCheckDto);
                    return backgroundCheckDto;
                }).collect(Collectors.toList());
        return backgroundCheckDtos;


    }
}
