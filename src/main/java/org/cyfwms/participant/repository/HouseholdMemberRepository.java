package org.cyfwms.participant.repository;
import org.cyfwms.participant.entity.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
    @Query(value = "select * from householdmember where participantId=? AND status='ACTIVE'",nativeQuery = true)
    List<HouseholdMember> findByParticipantId(Long participantId);
    @Query(value = "select * from householdmember where householdMemberId=? AND status='ACTIVE'",nativeQuery = true)
    List<HouseholdMember> findByHouseholdMemberId(Long householdMemberId);
    @Query(value = "select * from householdmember where status='ACTIVE'",nativeQuery = true)
    List<HouseholdMember> findAllHouseholdMember();

  //  Optional<HouseholdMember> findByparticipantId(Long participantId);
}
