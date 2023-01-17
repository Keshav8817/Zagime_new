package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.HouseholdMemberDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HouseholdMemberService {
    List<HouseholdMemberDto> getAllHouseholdMembers(Long participantId);
    List<HouseholdMemberDto> saveAllHouseholdMembers(List<HouseholdMemberDto> HouseholdMemberDtoList);
    List<HouseholdMemberDto> removeHouseholdMembers(Long householdMemberId);


}
