package org.cyfwms.staff.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff_goalsandobjectives")
public class StaffGoalsAndObjectives implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "goalsandobjectivesid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "goalsAndObjectivesIdGenerator",
            sequenceName = "goalsAndObjectivesIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "goalsAndObjectivesIdGenerator"
    )
    private Long goalsAndObjectivesId;

    @Getter @Setter @Column(name = "date")
    private LocalDate date;

    @Getter @Setter @Column(name = "status")
    private String status;

    @Getter @Setter @Column(name = "supervisor")
    private String supervisor;

    @Getter @Setter @Column(name = "planperiod")
    private String planPeriod;

    @Getter @Setter @Column(name = "goalsandobjectives")
    @Lob
    private String goalsAndObjectives;

    @Getter @Setter @Column(name = "guidesandenablers")
    @Lob
    private String guidesAndEnablers;

    @Getter @Setter @Column(name = "reviewcomments")
    @Lob
    private String reviewComments;

    @Getter @Setter @Column(name = "reviewedby")
    private String reviewedBy;

    @Getter @Setter @Column(name = "reviewdate")
    private LocalDate reviewDate;

    @Getter @Setter @Column(name = "statusofdeletion")
    private String statusOfDeletion;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;

    @Getter @Setter @Column(name = "staffid")
    private Long staffId;
}
