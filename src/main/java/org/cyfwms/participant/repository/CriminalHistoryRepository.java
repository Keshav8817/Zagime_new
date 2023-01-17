package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.CriminalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CriminalHistoryRepository extends JpaRepository<CriminalHistory, Long> {
    @Query(value = "select * from criminalhistory where participantid=? AND status='ACTIVE'",nativeQuery = true)
    CriminalHistory findByParticipantId(Long participantId);
}
