package org.cyfwms.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import java.time.LocalTime;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long appointmentId;
    private String subject;
    private String status;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String duration;
    private String client;
    private String caseworker;
    private String recurringAppointment;
    private String frequency;
    private LocalDate endDate;
    private String notes;
    private Long participantId;

}
