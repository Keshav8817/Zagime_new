package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHSafetyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FHSafetyPlanRepository extends JpaRepository<FHSafetyPlan,Long> {
    @Query(value = "select * from fh_safetyplan where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    List<FHSafetyPlan> findByFhFileDetailsId(Long fhFileDetailsId);
}
