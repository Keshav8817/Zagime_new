package org.cyfwms.staff.service;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.StaffContactInformationDto;
import org.cyfwms.staff.entity.StaffContactInformation;
import org.cyfwms.staff.repository.StaffContactInformationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class StaffContactInformationServiceImpl implements StaffContactInformationService {
    @Autowired
    private StaffContactInformationRepository staffContactInformationRepo;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public StaffContactInformationDto saveContactInformation(StaffContactInformationDto contactInformationDto) {
        log.info("Inside saveContactInformation");
        StaffContactInformation staffContactInformation = null;
        if (contactInformationDto.getStaffContactInformationId() == 0) {
            staffContactInformation = new StaffContactInformation();
            BeanUtils.copyProperties(contactInformationDto, staffContactInformation);
            staffContactInformation.setStatus("ACTIVE");
        } else {
            staffContactInformation = staffContactInformationRepo.findById(contactInformationDto.getStaffContactInformationId()).get();
            BeanUtils.copyProperties(contactInformationDto, staffContactInformation);
        }
        staffContactInformation = staffContactInformationRepo.save(staffContactInformation);
        contactInformationDto.setStaffId(staffContactInformation.getStaffId());
        contactInformationDto.setStaffContactInformationId(staffContactInformation.getStaffContactInformationId());
        log.info("Exit saveContactInformation");
        return contactInformationDto;
    }

    @Override
    public StaffContactInformationDto readStaffContactInformation(Long staffId) {
        log.info("Inside ReadStaffContactInformation");
        StaffContactInformationDto staffContactInformationDto = new StaffContactInformationDto();
        if (staffId!=0) {
            StaffContactInformation staffContactInformation = staffContactInformationRepo.findByStaffId(staffId).orElseThrow(() ->
                    new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(staffId))));

            BeanUtils.copyProperties(staffContactInformation, staffContactInformationDto);
            log.info("Exit ReadStaffContactInformation");
        }
        return staffContactInformationDto;
    }
}
