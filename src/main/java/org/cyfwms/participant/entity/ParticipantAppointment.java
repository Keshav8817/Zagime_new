package org.cyfwms.participant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.cyfwms.common.entity.Appointments;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name="participant_appointment")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ParticipantAppointment implements Serializable {
    @Id
    @Column(name="participantappointmentid", nullable = false )
    @Getter
    @Setter
    @SequenceGenerator(
            name = "participantappointmentIdGenerator",
            sequenceName = "participantappointmentIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participantappointmentIdGenerator"
    )
    private Long participantAppointmentId;

    @Getter @Setter @Column(name = "participantid")
    private Long participantId;

    @Getter @Setter @Column(name = "referenceid")
    private Long referenceId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "appointmentid", referencedColumnName = "appointmentid")
    @Getter @Setter

    private Appointments appointments;


    @Getter @Setter @Column(name = "status")
    private String status;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;

    @CreationTimestamp
    @Getter @Setter @Column(name= "creationdate")
    private LocalDateTime creationDate;

}
