package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.FamilyPhysicianDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface FamilyPhysicianService {
    List<FamilyPhysicianDto> getAllFamilyPhysicians(Long participantId);
    List<FamilyPhysicianDto> saveAllFamilyPhysicians(List<FamilyPhysicianDto> FamilyPhysicianDtoList);
    List<FamilyPhysicianDto> removeFamilyPhysician(Long familyPhysicianId);
}
