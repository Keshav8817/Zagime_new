package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.ParticipantContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantContactRepository extends JpaRepository<ParticipantContact, Long> {
    @Query(value = "select * from participantcontact where participantId=? AND status='ACTIVE'",nativeQuery = true)

    ParticipantContact findByParticipantId(long participantId);
}
