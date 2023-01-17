package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICPatientCareInfoDto;

public interface ICPatientCareInfoService {
    ICPatientCareInfoDto readAllPatientCareInfo(Long fileDetailsId);
    ICPatientCareInfoDto saveAllPatientCareInfo(ICPatientCareInfoDto initialContactPatientCareInfoDto);
}
