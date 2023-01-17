package org.cyfwms.staff.repository;

import org.cyfwms.staff.entity.StaffContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StaffContactInformationRepository extends JpaRepository<StaffContactInformation,Long> {
    @Query(value = "select * from staff_contactinformation where staffId=? AND status='ACTIVE'",nativeQuery = true)
    Optional<StaffContactInformation> findByStaffId(Long staffId);
}
