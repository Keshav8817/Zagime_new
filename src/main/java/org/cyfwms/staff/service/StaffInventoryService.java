package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.StaffInventoryDto;

import java.util.List;

public interface StaffInventoryService {
    List<StaffInventoryDto> getAllInventories(Long staffId);

    List<StaffInventoryDto> saveAllInventories(List<StaffInventoryDto> staffInventoryDtoList);
    List<StaffInventoryDto> removeInventory(Long staffInventoryId);
}
