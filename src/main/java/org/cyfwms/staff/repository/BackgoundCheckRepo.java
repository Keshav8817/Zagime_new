package org.cyfwms.staff.repository;

import org.cyfwms.staff.entity.BackgroundCheck;
import org.cyfwms.staff.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackgoundCheckRepo extends JpaRepository<BackgroundCheck,Long> {
    @Query(value = "select * from background_check c1  where c1.status_of_deletion ='ACTIVE' AND c1.staffid=? ", nativeQuery = true)
    List<BackgroundCheck> findByStaffId(Long StaffId);
}
