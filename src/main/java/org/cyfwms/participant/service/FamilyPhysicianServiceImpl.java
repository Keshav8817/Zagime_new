package org.cyfwms.participant.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.FamilyPhysicianDto;
import org.cyfwms.participant.dto.HouseholdMemberDto;
import org.cyfwms.participant.entity.FamilyPhysician;
import org.cyfwms.participant.entity.HouseholdMember;
import org.cyfwms.participant.repository.FamilyPhysicianRepository;
import lombok.AllArgsConstructor;
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
public class FamilyPhysicianServiceImpl implements FamilyPhysicianService {
    @Autowired
    private FamilyPhysicianRepository familyPhysicianRepository;

    @Override
    public List<FamilyPhysicianDto> getAllFamilyPhysicians(Long participantId) {
        log.info("Inside GetAllFamilyPhysicians");
        List<FamilyPhysicianDto> familyPhysicianDtoList = new ArrayList<FamilyPhysicianDto>();
        if(participantId != 0){
            familyPhysicianDtoList =
                    familyPhysicianRepository.findByParticipantId(participantId)
                            .stream()
                            .map( fp -> {
                                FamilyPhysicianDto fpDto = new FamilyPhysicianDto();
                                BeanUtils.copyProperties(fp, fpDto);
                                return fpDto;
                            }).collect(Collectors.toList());
        }
        log.info("Exit GetAllFamilyPhysicians");
        return familyPhysicianDtoList;
    }

    @Override
    public List<FamilyPhysicianDto> saveAllFamilyPhysicians(List<FamilyPhysicianDto> FamilyPhysicianDtoList) {
        log.info("Inside SaveAllFamilyPhysicians");
        for (FamilyPhysicianDto FamilyPhysicianDto: FamilyPhysicianDtoList) {
            FamilyPhysician familyPhysician = null;
            if (FamilyPhysicianDto.getFamilyPhysicianId() == 0) {
                familyPhysician = new FamilyPhysician();
                BeanUtils.copyProperties(FamilyPhysicianDto, familyPhysician);
                familyPhysician.setStatus("ACTIVE");
            } else {
                familyPhysician = familyPhysicianRepository.findById(FamilyPhysicianDto.getFamilyPhysicianId()).get();
                BeanUtils.copyProperties(FamilyPhysicianDto, familyPhysician);
            }
            familyPhysician = familyPhysicianRepository.save(familyPhysician);
            FamilyPhysicianDto.setFamilyPhysicianId(familyPhysician.getFamilyPhysicianId());
        }
        log.info("Exit SaveAllFamilyPhysicians");
        return FamilyPhysicianDtoList;
    }

    @Override
    public List<FamilyPhysicianDto> removeFamilyPhysician(Long familyPhysicianId) {
        log.info("Inside RemoveFamilyPhysicians");
        Long participantId=0l;
        Optional<FamilyPhysician> familyPhysicianOpt =
                familyPhysicianRepository.findById(familyPhysicianId);
        if(familyPhysicianOpt.isPresent()){
            FamilyPhysician familyPhysician = familyPhysicianOpt.get();
            participantId=familyPhysicianOpt.get().getParticipantId();
            familyPhysician.setStatus("INACTIVE");
            familyPhysicianRepository.save(familyPhysician);


        }
        List<FamilyPhysicianDto> familyPhysicianDtoList = getAllFamilyPhysicians(participantId);


        log.info("Exit RemoveFamilyPhysicians");
        return  familyPhysicianDtoList;
    }
}
