package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICParticipantRepository extends JpaRepository<ICParticipant,Long> {
    @Query(value = "select * from icparticipant i where i.fileDetailsId=? AND i.status='ACTIVE'",nativeQuery = true)
    ICParticipant findByFileDetailsId(Long fileDetailsId);

}
