package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.PatientCareInfoOutpatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientCareInfoOutpatientRepository extends JpaRepository<PatientCareInfoOutpatient,Long> {
    @Query(value = "select * from patientcareinfooutpatient where patientCareInfoId=? AND status='ACTIVE'",nativeQuery = true)
    PatientCareInfoOutpatient findByPatientCareInfoId(Long patientCareInfoId);
}
