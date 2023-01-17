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
@Table(name = "fh_safetyplan")
public class FHSafetyPlan {
    @Id
    @Getter
    @Setter
    @Column(name = "fhsafetyplanid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhSafetyPlanIdGenerator",
            sequenceName = "fhSafetyPlanIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhSafetyPlanIdGenerator"
    )
    private Long fhSafetyPlanId;

    @Getter
    @Setter
    @Column(name = "date")
    private LocalDate date;

    @Getter
    @Setter
    @Column(name = "participant")
    private String participant;

    @Getter
    @Setter
    @Column(name = "signs")
    @Lob
    private String signs;

    @Getter
    @Setter
    @Column(name = "strategies")
    @Lob
    private String strategies;

    @Getter
    @Setter
    @Column(name = "crisis")
    @Lob
    private String crisis;

    @Getter
    @Setter
    @Column(name = "printname")
    private String printName;

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
