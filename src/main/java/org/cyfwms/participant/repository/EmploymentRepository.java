package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    @Query(value = "select * from employment where participantId=? AND status='ACTIVE'",nativeQuery = true)

    Employment findByParticipantId(Long participantId);

    Optional<Employment> findByparticipantId(Long participantId);
}
