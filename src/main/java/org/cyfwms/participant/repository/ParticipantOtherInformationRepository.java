package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.ParticipantOtherInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantOtherInformationRepository extends JpaRepository<ParticipantOtherInformation, Long> {
    @Query(value = "select * from participantotherinformation where participantId=? AND status='ACTIVE'",nativeQuery = true)

    ParticipantOtherInformation findByParticipantId(long participantId);
}
