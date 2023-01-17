package org.cyfwms.participant.service;


import org.cyfwms.participant.dto.ParticipantContactDto;

public interface ParticipantContactService {

    ParticipantContactDto readParticipantContact(Long participantId);

    ParticipantContactDto saveParticipantContact(ParticipantContactDto ParticipantContactDto);
}
