package org.cyfwms.familyhealing.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fh_progressreport")
public class FHProgressReport {
    @Id
    @Getter
    @Setter
    @Column(name = "fhprogressreportid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhProgressReportIdGenerator",
            sequenceName = "fhProgressReportIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhProgressReportIdGenerator"
    )
    private Long fhProgressReportId;

    @Getter
    @Setter
    @Column(name = "dateofsssessment")
    private LocalDate dateOfAssessment;

    @Getter
    @Setter
    @Column(name = "completedby")
    private String completedBy;

    @Getter
    @Setter
    @Column(name = "areas")
    @Lob
    private String areas;

    @Getter
    @Setter
    @Column(name = "sessionsattended")
    @Lob
    private String sessionsAttended;

    @Getter
    @Setter
    @Column(name = "session")
    @Lob
    private String sessions;

    @Getter
    @Setter
    @Column(name = "sessions")
    @Lob
    private String progress;

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
    @Column(name = "fhfiledetailsid")
    private Long fhFileDetailsId;
}
