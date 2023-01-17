package org.cyfwms.staff.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.StaffDto;
import org.cyfwms.staff.entity.Staff;
import org.cyfwms.staff.repository.StaffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public StaffDto saveStaff(StaffDto staffDto) {
        log.info("Inside SaveStaff");
        Staff staff = null;
        if (staffDto.getStaffId() == 0) {
            staff = new Staff();
            BeanUtils.copyProperties(staffDto, staff);
            staff.setStatusOfDeletion("ACTIVE");
        } else {
            staff = staffRepository.findById(staffDto.getStaffId()).get();
            BeanUtils.copyProperties(staffDto, staff);
        }
        staff = staffRepository.save(staff);
        staffDto.setStaffId(staff.getStaffId());
        staffDto.setEmployeeId(staff.getEmployeeId());
        log.info("Exit SaveStaff");
        return staffDto;
    }

    @Override
    public StaffDto readStaff(Long staffId) {
        log.info("Inside ReadStaff");
        StaffDto staffDto = new StaffDto();
        if(staffId!=0){
        Staff staff = staffRepository.findById(
                staffId).filter(active -> active.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(staffId))));
        BeanUtils.copyProperties(staff, staffDto);

            if (staffDto.getSupervisor()!=null) {
                if(!staffDto.getSupervisor().equals("0")){
                    Optional<Staff> staffSearch = staffRepository.findById(Long.parseLong(staffDto.getSupervisor()));
                    staffDto.setSupervisor(staffSearch.get().getFirstName() + " " + staffSearch.get().getLastName());
                    staffDto.setSuperviserId(staffSearch.get().getStaffId());
                }

            }
            log.info("Exit ReadStaff");
        }
        return staffDto;
    }

    @Override
    public void removeStaff(Long staffId) {
        log.info("Inside RemoveStaff");
        Staff s =
                staffRepository.findById(staffId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(staffId))));

        if (s.getStatusOfDeletion().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(staffId)));
        }
        s.setStatusOfDeletion("INACTIVE");
        log.info("Exit RemoveStaff");
        staffRepository.save(s);
    }
}
