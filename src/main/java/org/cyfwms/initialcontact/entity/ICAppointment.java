package org.cyfwms.initialcontact.entity;

import lombok.*;
import org.cyfwms.common.entity.Appointments;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Builder
@Table(name="ic_appointment")
@AllArgsConstructor
@NoArgsConstructor
public class ICAppointment implements Serializable {
    @Id
    @Column(name="icappointmentid", nullable = false )
    @Getter
    @Setter
    @SequenceGenerator(
            name = "icAppointmentIdGenerator",
            sequenceName = "icAppointmentIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "icAppointmentIdGenerator"
    )
    private Long icappointmentId;

    @Getter @Setter @Column(name = "filedetailsid")
    private Long fileDetailsId;
    @Getter @Setter @Column(name = "filedetails_no")
    private Long fileDetailsNo;

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
    @Getter @Setter @Column(name= "creationdate",updatable = false)
    private LocalDateTime creationDate;

}
