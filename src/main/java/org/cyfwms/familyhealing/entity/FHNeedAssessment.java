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
@Table(name = "fh_needassessment")
public class FHNeedAssessment {
    @Id
    @Getter
    @Setter
    @Column(name = "fhneedassessmentid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhNeedAssessmentIdGenerator",
            sequenceName = "fhNeedAssessmentIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhNeedAssessmentIdGenerator"
    )
    private Long fhNeedAssessmentId;

    @Getter
    @Setter
    @Column(name = "assessmentdate")
    private LocalDate assessmentDate;

    @Getter
    @Setter
    @Column(name = "completedby")
    private String completedBy;

    @Getter
    @Setter
    @Column(name = "family")
    @Lob
    private String family;

    @Getter
    @Setter
    @Column(name = "identified")
    @Lob
    private String identified;

    @Getter
    @Setter
    @Column(name = "address")
    @Lob
    private String address;

    @Getter
    @Setter
    @Column(name = "resources")
    @Lob
    private String resources;

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
