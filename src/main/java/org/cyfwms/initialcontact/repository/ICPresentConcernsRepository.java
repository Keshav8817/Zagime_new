package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICPresentConcerns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICPresentConcernsRepository extends JpaRepository<ICPresentConcerns, Long> {
    @Query(value = "select * from ic_presentconcerns where fileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    ICPresentConcerns findByFileDetailsId(Long fileDetailsId);
}
