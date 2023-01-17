package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICPatientCareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICPatientCareInfoRepository extends JpaRepository<ICPatientCareInfo,Long> {
    @Query(value = "select * from ic_patientcareinfo where fileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    ICPatientCareInfo findByFileDetailsId(Long fileDetailsId);
}
