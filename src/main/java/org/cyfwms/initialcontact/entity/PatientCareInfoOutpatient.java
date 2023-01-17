package org.cyfwms.initialcontact.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patientcareinfooutpatient")
public class PatientCareInfoOutpatient {
    @Id
    @Getter
    @Setter
    @Column(name = "outpatientid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "outpatientidgenerator",
            sequenceName = "outpatientidgenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "outpatientidgenerator"
    )
    private Long outpatientId;

    @Getter @Setter @Column(length = 1000,name = "therapyorcounseling")
    private String therapyOrCounseling;

    @Getter @Setter @Column(name = "therapytimeperiod")
    private String therapyTimePeriod;

    @Getter @Setter @Column(name = "therapylocation")
    private String therapyLocation;

    @Getter @Setter @Column(length = 1000,name = "reasonfortherapy")
    private String reasonForTherapy;

    @Getter @Setter @Column(name = "selfhelpgroup")
    private String selfHelpGroup;

    @Getter @Setter @Column(name = "selfhelpgroupperiod")
    private String selfHelpGroupPeriod;

    @Getter @Setter @Column(name = "selfhelpgrouplocation")
    private String selfHelpGroupLocation;

   @Getter @Setter @Column(name = "patientcareinfoid")
    private Long patientCareInfoId;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate",updatable = false)
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;

    @Getter @Setter @Column(name = "status")
    private String status;
}
