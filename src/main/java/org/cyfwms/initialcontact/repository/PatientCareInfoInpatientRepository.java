package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.PatientCareInfoInpatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientCareInfoInpatientRepository extends JpaRepository<PatientCareInfoInpatient,Long> {
    @Query(value = "select * from patientcareinfoinpatient where patientCareInfoId=? AND status='ACTIVE'",nativeQuery = true)
    PatientCareInfoInpatient findByPatientCareInfoId(Long patientCareInfoId);
}
