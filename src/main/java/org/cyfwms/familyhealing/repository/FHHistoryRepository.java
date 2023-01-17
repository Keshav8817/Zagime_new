package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FHHistoryRepository extends JpaRepository<FHHistory,Long> {
    @Query(value = "select * from fh_history where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)

    Optional<FHHistory> findByFhFileDetailsId(Long fhFileDetailsId);
}
