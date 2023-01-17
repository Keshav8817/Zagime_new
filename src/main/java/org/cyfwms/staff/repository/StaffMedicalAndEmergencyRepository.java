package org.cyfwms.staff.repository;
import org.cyfwms.staff.entity.StaffMedicalAndEmergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffMedicalAndEmergencyRepository extends JpaRepository<StaffMedicalAndEmergency,Long> {
    @Query(value = "select * from staff_medicalandemergency where staffId=? AND status='ACTIVE'",nativeQuery = true)
    Optional<StaffMedicalAndEmergency> findByStaffId(Long staffId);
}