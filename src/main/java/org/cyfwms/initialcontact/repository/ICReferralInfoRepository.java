package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICReferralInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICReferralInfoRepository extends JpaRepository<ICReferralInfo, Long> {
    @Query(value = "select * from ic_referralinfo where fileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    ICReferralInfo findByFileDetailsId(Long fileDetailsId);
}
