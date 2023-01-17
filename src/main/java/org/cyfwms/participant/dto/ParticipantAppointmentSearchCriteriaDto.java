package org.cyfwms.participant.dto;

import lombok.Getter;
import lombok.Setter;

public class ParticipantAppointmentSearchCriteriaDto {
    @Getter
    @Setter
    private Long participantId;
    @Getter @Setter
    private String data;
    @Getter@Setter
    private Long participantAppointmentId;
}
