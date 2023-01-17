package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICFileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICFileDetailsRepository extends JpaRepository<ICFileDetails, Long> {
    Optional<ICFileDetails> findTopByOrderByFileNumberDesc();

   @Query(value = "select * from icfiledetails i where i.statusofdeletion='ACTIVE' AND fileNumber=?",nativeQuery = true)
    Optional<ICFileDetails> findByFileNumber(Long fileNumber);

    ICFileDetails findByFileDetailsId(Long fileDetailsId);
}
