package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHCasePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FHCasePlanRepository extends JpaRepository<FHCasePlan,Long> {
    @Query(value = "select * from fh_caseplan where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    List<FHCasePlan> findByFhFileDetailsId(Long fhFileDetailsId);
}
