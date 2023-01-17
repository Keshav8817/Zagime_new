package org.cyfwms.staff.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.StaffJobAndBankingDto;
import org.cyfwms.staff.entity.StaffJobAndBanking;
import org.cyfwms.staff.repository.StaffJobAndBankingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StaffJobAndBankingServiceImpl implements StaffJobAndBankingService {
    @Autowired
    private StaffJobAndBankingRepository staffJobAndBankingRepo;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public StaffJobAndBankingDto saveStaffJobAndBanking(StaffJobAndBankingDto staffJobAndBankingDto) {
        log.info("Inside saveContactInformation");
        StaffJobAndBanking staffJobAndBanking = null;
        if (staffJobAndBankingDto.getStaffJobAndBankingId() == 0) {
            staffJobAndBanking = new StaffJobAndBanking();
            BeanUtils.copyProperties(staffJobAndBankingDto, staffJobAndBanking);
            staffJobAndBanking.setStatus("ACTIVE");
        } else {
            staffJobAndBanking = staffJobAndBankingRepo.findById(staffJobAndBankingDto.getStaffJobAndBankingId()).get();
            BeanUtils.copyProperties(staffJobAndBankingDto, staffJobAndBanking);
        }
        staffJobAndBanking = staffJobAndBankingRepo.save(staffJobAndBanking);
        staffJobAndBankingDto.setStaffId(staffJobAndBanking.getStaffId());
        staffJobAndBankingDto.setStaffJobAndBankingId(staffJobAndBanking.getStaffJobAndBankingId());
        log.info("Exit saveContactInformation");
        return staffJobAndBankingDto;
    }

    @Override
    public StaffJobAndBankingDto readStaffJobAndBanking(Long staffId) {
        log.info("Inside ReadStaffJobAndBanking");
        StaffJobAndBankingDto staffJobAndBankingDto = new StaffJobAndBankingDto();
        Optional<StaffJobAndBanking> staffContactInformation = Optional.ofNullable(staffJobAndBankingRepo.findByStaffId(staffId).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                        String.valueOf(staffId)))));

        BeanUtils.copyProperties(staffContactInformation.get(), staffJobAndBankingDto);
        log.info("Exit ReadStaffJobAndBanking");
        return staffJobAndBankingDto;
    }
}
