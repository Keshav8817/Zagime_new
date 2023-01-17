package org.cyfwms.staff.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.staff.dto.StaffInventoryDto;
import org.cyfwms.staff.entity.StaffInventory;
import org.cyfwms.staff.repository.StaffInventoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StaffInventoryServiceImpl implements StaffInventoryService {
    @Autowired
    private StaffInventoryRepository staffInventoryRepo;

    @Override
    public List<StaffInventoryDto> getAllInventories(Long staffId) {
        log.info("Inside GetAllInventories");
        List<StaffInventoryDto> staffInventoryDtoList = new ArrayList<StaffInventoryDto>();
        if (staffId != 0) {
            staffInventoryDtoList =
                    staffInventoryRepo.findByStaffId(staffId)
                            .stream()
                            .map(fp -> {
                                StaffInventoryDto fpDto = new StaffInventoryDto();
                                BeanUtils.copyProperties(fp, fpDto);
                                return fpDto;
                            }).collect(Collectors.toList());
        }
        log.info("Exit GetAllInventories");
        return staffInventoryDtoList;
    }

    @Override
    public List<StaffInventoryDto> saveAllInventories(List<StaffInventoryDto> StaffInventoryDtoList) {
        log.info("Inside SaveAllInventories");
        for (StaffInventoryDto StaffInventoryDto : StaffInventoryDtoList) {
            StaffInventory staffInventory = null;
            if (StaffInventoryDto.getStaffInventoryId() == 0) {
                staffInventory = new StaffInventory();
                BeanUtils.copyProperties(StaffInventoryDto, staffInventory);
                staffInventory.setStatus("ACTIVE");
            } else {
                staffInventory = staffInventoryRepo.findById(StaffInventoryDto.getStaffInventoryId()).get();
                BeanUtils.copyProperties(StaffInventoryDto, staffInventory);
            }
            staffInventory = staffInventoryRepo.save(staffInventory);
            StaffInventoryDto.setStaffInventoryId(staffInventory.getStaffInventoryId());
        }
        log.info("Exit SaveAllInventories");
        return StaffInventoryDtoList;
    }

    @Override
    public List<StaffInventoryDto> removeInventory(Long staffInventoryId) {
        log.info("Inside RemoveInventory");
        Long staffId=0l;
        Optional<StaffInventory> StaffInventoryOpt =
                staffInventoryRepo.findById(staffInventoryId);
        if (StaffInventoryOpt.isPresent()) {
            StaffInventory staffInventory = StaffInventoryOpt.get();
            staffId=StaffInventoryOpt.get().getStaffId();
            staffInventory.setStatus("INACTIVE");
            staffInventoryRepo.save(staffInventory);


        }
        List<StaffInventoryDto> staffInventoryDtoList = getAllInventories(staffId);
        log.info("Exit RemoveInventory");
        return staffInventoryDtoList;
    }
}
