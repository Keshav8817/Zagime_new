package org.cyfwms.participant.service;

import org.cyfwms.common.dto.AppointmentDto;
import org.cyfwms.participant.dto.ParticipantAppointmentDto;

import java.util.List;

public interface ParticipantAppointmentService {
    List<ParticipantAppointmentDto> saveParticipantAppointment(ParticipantAppointmentDto participantAppointmentDto);

    void removeParticipantAppointment(Long participantAppointmentId);
    ParticipantAppointmentDto readOneAppointment(Long participantAppointment);
}
