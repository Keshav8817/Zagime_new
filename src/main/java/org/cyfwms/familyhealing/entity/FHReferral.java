package org.cyfwms.familyhealing.entity;
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
@Builder
@Entity
@Table(name = "fh_referral")
public class FHReferral implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "fhreferralid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhReferralIdGenerator",
            sequenceName = "fhReferralIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhReferralIdGenerator"
    )
    private Long fhReferralId;

    @Getter
    @Setter
    @Column(name = "referraldate")
    private LocalDate referralDate;


    @Getter
    @Setter
    @Column(name = "referredby")
    private String referredBy;

    @Getter
    @Setter
    @Column(name = "reason")
    private String reason;

    @Getter
    @Setter
    @Column(name = "pleasedescribe")
    @Lob
    private String pleaseDescribe;

    @Getter
    @Setter
    @Column(name = "notes")
    @Lob
    private String notes;

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
