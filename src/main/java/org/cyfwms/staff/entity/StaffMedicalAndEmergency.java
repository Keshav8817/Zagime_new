package org.cyfwms.staff.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff_medicalandemergency")
public class StaffMedicalAndEmergency {
    @Id
    @Getter
    @Setter
    @Column(name = "staffmedicalandemergencyid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "staffMedicalAndEmergencyIdGenerator",
            sequenceName = "staffMedicalAndEmergencyIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staffMedicalAndEmergencyIdGenerator"
    )
    private Long staffMedicalAndEmergencyId;

    @Getter
    @Setter
    @Column(name = "notes")
    @Lob
    private String notes;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "address")
    private String address;

    @Getter
    @Setter
    @Column(name = "phone")
    private String phone;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "relationship")
    private String relationship;

    @Getter
    @Setter
    @Column(name = "alternativecontactmethod")
    @Lob
    private String AlternativeContactMethod;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Getter
    @Setter
    @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter
    @Setter
    @Column(name = "lastwritten")
    private LocalDateTime lastwritten;

    @Getter
    @Setter
    @Column(name = "staffid")
    private Long staffId;
}
