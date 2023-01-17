package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query(value = "select * from education where participantId=? AND status='ACTIVE'",nativeQuery = true)

    Education findByParticipantId(Long participantId);

    Optional<Education> findByparticipantId(Long participantId);
}
