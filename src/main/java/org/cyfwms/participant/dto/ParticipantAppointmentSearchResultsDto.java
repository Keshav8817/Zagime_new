package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ParticipantAppointmentSearchResultsDto {
    @Getter
    @Setter
    private Long appointmentId;

    @Getter
    @Setter
    private Long participantId;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private LocalDate date;
    @Getter
    @Setter
    private Long participantAppointmentId;
    private LocalTime time;
    private String location;
    private String duration;
    private String client;
    private String caseworker;
    private String recurringAppointment;
    private String frequency;
    @Getter @Setter
    private LocalDate endDate;
    private String notes;

    public ParticipantAppointmentSearchResultsDto(Long appointmentId, Long participantId, String subject, String status, LocalDate date, Long participantAppointmentId, LocalTime time, String location, String duration, String client, String caseworker, String recurringAppointment, String frequency, LocalDate endDate, String notes) {
        this.appointmentId = appointmentId;
        this.participantId = participantId;
        this.subject = subject;
        this.status = status;
        this.date = date;
        this.participantAppointmentId = participantAppointmentId;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.client = client;
        this.caseworker = caseworker;
        this.recurringAppointment = recurringAppointment;
        this.frequency = frequency;
        this.endDate = endDate;
        this.notes = notes;
    }
}
