package org.cyfwms.common.util;

import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReferenceIDGeneratorUtil {

    @Autowired
    private ParticipantRepository participantRepo;



    public Long generateParticipantReferenceID() {
        Long referenceId = 128L;
        Optional<Participant> participantOpt =
                participantRepo.findTopByOrderByCreationDateTimeDesc();
        if (participantOpt.isPresent()) {
            referenceId = participantOpt.get().getReferenceId() + 128L;
        }
        return referenceId;
    }


}
