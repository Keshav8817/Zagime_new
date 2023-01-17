package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.ParticipantOtherInformationServiceDto;

public interface ParticipantOtherInformationService {
    ParticipantOtherInformationServiceDto readParticipantOtherInformation(Long participantId);

    ParticipantOtherInformationServiceDto saveParticipantOtherInformation(ParticipantOtherInformationServiceDto participantOtherInformationServiceDto);
}
