package org.cyfwms.staff.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.staff.dto.StaffSearchCriteriaDto;
import org.cyfwms.staff.dto.StaffSearchResultsDto;
import org.cyfwms.staff.repository.StaffSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class StaffSearchService {
    @Autowired
    private StaffSearchRepository staffSearchRepository;
    public List<StaffSearchResultsDto> searchStaff(StaffSearchCriteriaDto staffSearchCriteriaDto) {
        log.info("StaffSearchCriteriaDto :"+staffSearchCriteriaDto);
        return staffSearchRepository.searchStaffs(staffSearchCriteriaDto);
    }
}
