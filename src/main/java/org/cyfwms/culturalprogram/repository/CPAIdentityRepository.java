package org.cyfwms.culturalprogram.repository;
import org.cyfwms.culturalprogram.entity.CPAIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CPAIdentityRepository extends JpaRepository<CPAIdentity, Long> {
    Optional<CPAIdentity> findTopByOrderByReferenceIdDesc();

    @Query(value = "select * from culturalprogandact c where c.culturalprogramid=? AND c.deletionofstatus='ACTIVE'",nativeQuery = true)
    CPAIdentity findByCulturalProgramId(Long culturalProgramId);
}
