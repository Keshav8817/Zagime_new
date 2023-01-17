package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHNeedAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FHNeedAssessmentRepository extends JpaRepository<FHNeedAssessment,Long> {
    @Query(value = "select * from fh_needassessment where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    List<FHNeedAssessment> findByFhFileDetailsId(Long fhFileDetailsId);
}
