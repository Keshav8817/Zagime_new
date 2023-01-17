package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.entity.FHConsentForms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FHConsentFormsRepo extends JpaRepository<FHConsentForms,Long> {
    @Query(value = "select * from fh_consentforms where fhFileDetailsId=? AND status='ACTIVE'",nativeQuery = true)
    List<FHConsentForms> findByFhFileDetailsId(Long fhFileDetailsId);
}
