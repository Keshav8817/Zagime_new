package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FHApprovalRepository extends JpaRepository<FHApproval,Long> {
    @Query(value = "select * from fh_approval where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    Optional<FHApproval> findByFhFileDetailsId(Long fhFileDetailsId);
}
