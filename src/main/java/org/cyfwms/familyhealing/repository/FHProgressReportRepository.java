package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHProgressReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FHProgressReportRepository extends JpaRepository<FHProgressReport,Long> {
    @Query(value = "select * from fh_progressreport where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)

    List<FHProgressReport> findByFhFileDetailsId(Long fhFileDetailsId);
}
