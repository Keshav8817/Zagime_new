package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHReferral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FHReferralRepository extends JpaRepository<FHReferral,Long> {
    @Query(value = "select * from fh_referral where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)

    Optional<FHReferral> findByFhFileDetailsId(Long fhFileDetailsId);
}
