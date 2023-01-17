package org.cyfwms.staff.repository;

import org.cyfwms.staff.entity.StaffInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffInventoryRepository extends JpaRepository<StaffInventory,Long> {
    @Query(value = "select * from staff_inventory where staffId=? AND status='ACTIVE'",nativeQuery = true)
    List<StaffInventory> findByStaffId(Long staffId);
}
