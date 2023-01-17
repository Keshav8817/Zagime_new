package org.cyfwms.familyhealing.entity;
import lombok.*;
import org.cyfwms.common.entity.Attachment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fh_consentforms")
public class FHConsentForms {
    @Id
    @Getter
    @Setter
    @Column(name = "fhconsentformsid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhConsentFormsIdGenerator",
            sequenceName = "fhConsentFormsIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhConsentFormsIdGenerator"
    )
    private Long fhConsentFormsId;

    @Getter
    @Setter
    @Column(name = "date")
    private LocalDate date;

    @Getter
    @Setter
    @Column(name = "type")
    private String type;

    @Getter
    @Setter
    @Column(name = "pleasespecify")
    private String pleaseSpecify;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachmentid", referencedColumnName = "attachmentid",updatable = false)
    @Getter @Setter
    private Attachment attachment;
}
