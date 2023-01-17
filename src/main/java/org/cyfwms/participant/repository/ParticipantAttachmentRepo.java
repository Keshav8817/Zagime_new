package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.ParticipantAttachment;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParticipantAttachmentRepo extends JpaRepository<ParticipantAttachment, Long>{

    List<ParticipantAttachment>
            findByParticipantIdAndAttachmentTypeAndStatus(Long id,
                                                           String attachmentType, String status);
    @Query(value = "select * from participantattachment pa left join participant p on pa.participantid = p.participantid where pa.status ='ACTIVE' AND p.participantid=? ", nativeQuery = true)
    List<ParticipantAttachment> findByParticipantId(Long participantId);
}
