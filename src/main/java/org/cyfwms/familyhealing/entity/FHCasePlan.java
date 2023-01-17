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
@Entity
@Table(name = "fh_caseplan")
public class FHCasePlan {
    @Id
    @Getter
    @Setter
    @Column(name = "fhcaseplanid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhCasePlanIdGenerator",
            sequenceName = "fhCasePlanIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhCasePlanIdGenerator"
    )
    private Long fhCasePlanId;

    @Getter
    @Setter
    @Column(name = "date")
    private LocalDate date;

    @Getter
    @Setter
    @Column(name = "regarding")
    private String regarding;

    @Getter
    @Setter
    @Column(name = "emotional")
    @Lob
    private String emotional;

    @Getter
    @Setter
    @Column(name = "spiritual")
    @Lob
    private String spiritual;

    @Getter
    @Setter
    @Column(name = "mental")
    @Lob
    private String mental;

    @Getter
    @Setter
    @Column(name = "physical")
    @Lob
    private String physical;

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
