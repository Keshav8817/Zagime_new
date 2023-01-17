package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.FamilyPhysician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FamilyPhysicianRepository extends JpaRepository<FamilyPhysician, Long> {
    @Query(value = "select * from familyphysician where participantId=? AND status='ACTIVE'",nativeQuery = true)
    List<FamilyPhysician> findByParticipantId(Long participantId);
    @Query(value = "select * from familyphysician where familyPhysicianId=? AND status='ACTIVE'",nativeQuery = true)
    List<FamilyPhysician> findByFamilyPhysicianId(Long familyPhysicianId);

    @Query(value = "select * from familyphysician where  status='ACTIVE'",nativeQuery = true)
    List<FamilyPhysician> findAllFamilyPhysician();
}
