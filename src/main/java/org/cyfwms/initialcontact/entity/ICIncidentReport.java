package org.cyfwms.initialcontact.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ic_incidentreport")
public class ICIncidentReport implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "incidentreportid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "incidentreportidgenerator",
            sequenceName = "incidentreportidgenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "incidentreportidgenerator"
    )
    private Long incidentReportId;

    @Getter @Setter @Column(name = "reportedby")
    private String reportedBy;
    @Getter @Setter @Column(name = "partiesinvolved")
    private String partiesInvolved;
    @Getter @Setter @Column(name = "witnesses")
    private String witnesses;
    @Getter @Setter @Column(name = "incidentlocation")
    private String incidentLocation;
    @Getter @Setter @Column(name = "risk")
    private String risk;
    @Getter @Setter @Column(length=1000,name = "actiontaken")
    private String actionTaken;
    @Getter @Setter @Column(length=1000,name = "actionplan")
    private String actionPlan;

    @Getter @Setter @Column(name = "filedetailsid")
    private Long fileDetailsId;

    @Getter @Setter @Column(name = "dateofreport")
    private LocalDate dateOfReport;

    @Getter @Setter @Column(name = "incidentdate")
    private LocalDate incidentDate;

    @Getter @Setter @Column(name = "incidenttime")
    private LocalTime incidentTime;

    @Getter @Setter @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
}
