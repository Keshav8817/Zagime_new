package org.cyfwms.participant.repository;

import org.cyfwms.participant.entity.ParticipantAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipantAppointmentRepo extends JpaRepository<ParticipantAppointment,Long> {
    @Query(value = "select * from participant_appointment where appointmentid=? AND status='active'",nativeQuery = true)
    ParticipantAppointment findByAppointmentId(long AppointmentId);

    @Query(value = "select * from participant_appointment where participantid=? AND status='active'",nativeQuery = true)
    List<ParticipantAppointment> findByParticipantId(long participantId);
}
