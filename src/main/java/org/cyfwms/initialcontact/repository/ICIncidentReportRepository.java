package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICIncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICIncidentReportRepository extends JpaRepository<ICIncidentReport, Long> {
    @Query(value = "select * from ic_incidentreport where fileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    ICIncidentReport findByFileDetailsId(Long fileDetailsId);
}
