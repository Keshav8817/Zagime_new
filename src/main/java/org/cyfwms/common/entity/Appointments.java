package org.cyfwms.common.entity;

import lombok.*;
import org.cyfwms.initialcontact.entity.ICAppointment;
import org.cyfwms.participant.entity.ParticipantAppointment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointments implements Serializable {
    @Id
    @Column(name = "appointmentid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "appointmentIdGenerator",
            sequenceName = "appointmentIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointmentIdGenerator"
    )
    private Long appointmentId;
    @Column(name="subject")
    private String subject;
    @Column(name="status")
    private String status;
    @Column(name="date")
    private LocalDate date;
    @Column(name="time")
    private LocalTime time;
    @Column(name="location")
    private String location;
    @Column(name="duration")
    private String duration;
    @Column(name="client")
    private String client;
    @Column(name="caseworker")
    private String caseworker;
    @Column(name="recurring_appointment")
    private String recurringAppointment;
    @Column(name="frequency")
    private String frequency;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="notes")
    @Lob
    private String notes;
    @UpdateTimestamp
    @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
    @CreationTimestamp
    @Column(name="creationdate",updatable = false)
    private LocalDate creationDate;
    @Column(name="appointmentStatus")
    private String appointmentStatus;

    @OneToOne(mappedBy = "appointments")
    private ParticipantAppointment participantAppointment;

    @OneToOne(mappedBy = "appointments")
    private ICAppointment icAppointment;


}
