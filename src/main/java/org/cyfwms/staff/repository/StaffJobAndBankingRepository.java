package org.cyfwms.staff.repository;

import org.cyfwms.staff.entity.StaffJobAndBanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffJobAndBankingRepository extends JpaRepository<StaffJobAndBanking,Long> {
    @Query(value = "select * from staff_jobandbanking where staffId=? AND status='ACTIVE'",nativeQuery = true)
    Optional<StaffJobAndBanking> findByStaffId(Long staffId);
}
