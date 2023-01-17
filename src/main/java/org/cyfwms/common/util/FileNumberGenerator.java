package org.cyfwms.common.util;

import org.cyfwms.familyhealing.entity.FHFileDetails;
import org.cyfwms.familyhealing.repository.FHFileDetailsRepository;
import org.cyfwms.participant.entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FileNumberGenerator {
    @Autowired
    private FHFileDetailsRepository fHFileDetailsRepo;

    public Long generateFHFileDetailsFileNo() {
        Long fileNo = 1L;
        Optional<FHFileDetails> fHFileDetailsOpt =
                fHFileDetailsRepo.findTopByOrderByCreationDateTimeDesc();
        if (fHFileDetailsOpt.isPresent()) {
            fileNo = fHFileDetailsOpt.get().getFileNo() + 1L;
        }
        return fileNo;
    }
}
