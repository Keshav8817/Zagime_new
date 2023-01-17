package org.cyfwms.participant.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.HouseholdMemberDto;
import org.cyfwms.participant.entity.HouseholdMember;
import org.cyfwms.participant.repository.HouseholdMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class HouseholdMemberServiceImpl implements HouseholdMemberService {
    @Autowired
    private HouseholdMemberRepository householdMemberRepo;



    @Override
    public List<HouseholdMemberDto> getAllHouseholdMembers(Long participantId) {
          log.info("Inside GetAllHouseHoldMembers");
        List<HouseholdMemberDto> hmDtoList = householdMemberRepo.findByParticipantId(participantId)
                        .stream().
                        map(hm -> {
                            HouseholdMemberDto hmDTO = new HouseholdMemberDto();
                            BeanUtils.copyProperties(hm, hmDTO);
                            return hmDTO;
                        }).collect(Collectors.toList());
             log.info("Exit GetAllHouseHoldMembers");
        return hmDtoList;
    }

    @Override
    public List<HouseholdMemberDto> saveAllHouseholdMembers(List<HouseholdMemberDto> HouseholdMemberDtoList) {
        log.info("Inside SaveAllHouseHoldMembers");
        for (HouseholdMemberDto HouseholdMemberDto: HouseholdMemberDtoList) {
            HouseholdMember householdMember = null;
            if (HouseholdMemberDto.getHouseholdMemberId() == 0) {
                householdMember = new HouseholdMember();
                BeanUtils.copyProperties(HouseholdMemberDto, householdMember);
                householdMember.setStatus("ACTIVE");
            } else {
                householdMember = householdMemberRepo.findById(HouseholdMemberDto.getHouseholdMemberId()).get();
                BeanUtils.copyProperties(HouseholdMemberDto,householdMember);
            }
            householdMember = householdMemberRepo.save(householdMember);
            HouseholdMemberDto.setHouseholdMemberId(householdMember.getHouseholdMemberId());
        }
        log.info("Exit SaveAllHouseHoldMembers");
        return HouseholdMemberDtoList;
    }

    @Override
    public List<HouseholdMemberDto> removeHouseholdMembers(Long householdMemberId) {
        log.info("Inside RemoveHouseHoldMembers");
        Long participantId=0l;
        Optional<HouseholdMember> householdMemberOpt =
                householdMemberRepo.findById(householdMemberId);
        if(householdMemberOpt.isPresent()){
            HouseholdMember householdMember = householdMemberOpt.get();
            participantId = householdMemberOpt.get().getParticipantId();
            householdMember.setStatus("INACTIVE");
            log.info("Exit RemoveHouseHoldMembers");
            householdMemberRepo.save(householdMember);
        }
        List<HouseholdMemberDto> hmDtoList = getAllHouseholdMembers(participantId);
        return hmDtoList;

    }

}
