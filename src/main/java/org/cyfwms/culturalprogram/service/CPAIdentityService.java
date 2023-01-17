package org.cyfwms.culturalprogram.service;

import org.cyfwms.culturalprogram.dto.CPAIdentityDto;
public interface CPAIdentityService {
    CPAIdentityDto saveCpaIdentity(CPAIdentityDto CPAIdentityDto);
    CPAIdentityDto readCpaIdentity(Long culturalProgramId);
    void removeCpaIdentity(Long culturalProgramId);
}
