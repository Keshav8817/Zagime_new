package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.dto.FHFileDetailsDto;
import org.cyfwms.familyhealing.entity.FHFileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FHFileDetailsRepository extends JpaRepository<FHFileDetails,Long> {
    Optional<FHFileDetails> findTopByOrderByCreationDateTimeDesc();


}
