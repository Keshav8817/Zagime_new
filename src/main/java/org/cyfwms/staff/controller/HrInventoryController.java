package org.cyfwms.staff.controller;

import java.util.List;

import org.cyfwms.staff.dto.StaffInventoryDto;
import org.cyfwms.staff.service.StaffInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j(topic = "HrInventoryController")
@AllArgsConstructor
@RequestMapping("/v1/hrservice/inventory/")
@CrossOrigin("*")
public class HrInventoryController {
    @Autowired
    private StaffInventoryService hrInventoryService;

    @ApiOperation("Get/Read all inventory items.")
    @GetMapping(value = "read_all/{staffId}", produces = "application/json")
    public ResponseEntity<List<StaffInventoryDto>> getAllInventories(
        @PathVariable("staffId") Long staffId
    ) {
        log.info("GetAllInventories StaffId: " + staffId);
        return new ResponseEntity<List<StaffInventoryDto>>(
            hrInventoryService.getAllInventories(staffId),
            HttpStatus.OK
        );
    }


    @ApiOperation("Set/Save and update all inventory items.")
    @PutMapping(value = "save_all", produces = "application/json")
    public ResponseEntity<List<StaffInventoryDto>> saveAllInventories(
        @RequestBody List<StaffInventoryDto> StaffInventoryDtoList
    ) {
        log.info("SaveAllInventories: " + StaffInventoryDtoList);
        return new ResponseEntity<List<StaffInventoryDto>>(
            hrInventoryService.saveAllInventories(StaffInventoryDtoList),
            HttpStatus.CREATED
        );
    }

    @ApiOperation("Soft delete/remove one/single inventory item.")
    @DeleteMapping("remove_one/{staffInventoryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StaffInventoryDto> removeInventory(
        @PathVariable("staffInventoryId") Long staffInventoryId
    ) {
        log.info("RemoveInventory staffInventoryId: " + staffInventoryId);
       return hrInventoryService.removeInventory(staffInventoryId);
    }
}
