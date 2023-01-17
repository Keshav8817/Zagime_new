package org.cyfwms.staff.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.StaffContactInformationDto;
import org.cyfwms.staff.dto.StaffMedicalAndEmergencyDto;
import org.cyfwms.staff.entity.StaffContactInformation;
import org.cyfwms.staff.entity.StaffJobAndBanking;
import org.cyfwms.staff.entity.StaffMedicalAndEmergency;
import org.cyfwms.staff.repository.StaffJobAndBankingRepository;
import org.cyfwms.staff.repository.StaffMedicalAndEmergencyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StaffMedicalAndEmergencyServiceImpl implements StaffMedicalAndEmergencyService{
    @Autowired
    private StaffMedicalAndEmergencyRepository staffMedicalAndEmergencyRepo;
    @Autowired
    private MessageUtil messageUtil;
    @Override
    public StaffMedicalAndEmergencyDto saveStaffMedicalAndEmergency(StaffMedicalAndEmergencyDto medicalAndEmergencyDto) {
        log.info("Inside saveStaffMedicalAndEmergency");
        StaffMedicalAndEmergency staffMedicalAndEmergency = null;
        if (medicalAndEmergencyDto.getStaffMedicalAndEmergencyId() == 0) {
            staffMedicalAndEmergency = new StaffMedicalAndEmergency();
            BeanUtils.copyProperties(medicalAndEmergencyDto, staffMedicalAndEmergency);
            staffMedicalAndEmergency.setStatus("ACTIVE");
        } else {
            staffMedicalAndEmergency = staffMedicalAndEmergencyRepo.findById(medicalAndEmergencyDto.getStaffMedicalAndEmergencyId()).get();
            BeanUtils.copyProperties(medicalAndEmergencyDto, staffMedicalAndEmergency);
        }
        staffMedicalAndEmergency = staffMedicalAndEmergencyRepo.save(staffMedicalAndEmergency);
        medicalAndEmergencyDto.setStaffId(staffMedicalAndEmergency.getStaffId());
        medicalAndEmergencyDto.setStaffMedicalAndEmergencyId(staffMedicalAndEmergency.getStaffMedicalAndEmergencyId());
        log.info("Exit saveStaffMedicalAndEmergency");
        return medicalAndEmergencyDto;
    }

    @Override
    public StaffMedicalAndEmergencyDto readStaffMedicalAndEmergency(Long staffId) {
        log.info("Inside ReadStaffMedicalAndEmergency");
        StaffMedicalAndEmergencyDto staffContactInformationDto = new StaffMedicalAndEmergencyDto();
        if (staffId!=0) {
            Optional<StaffMedicalAndEmergency> staffContactInformation = Optional.ofNullable(staffMedicalAndEmergencyRepo.findByStaffId(staffId).orElseThrow(() ->
                    new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(staffId)))));
            BeanUtils.copyProperties(staffContactInformation.get(), staffContactInformationDto);
            log.info("Exit ReadStaffMedicalAndEmergency");
        }
        return staffContactInformationDto;
    }
}
