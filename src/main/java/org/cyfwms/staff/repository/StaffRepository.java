package org.cyfwms.staff.repository;

import org.cyfwms.staff.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByStaffId(long parseLong);
}
