package org.cyfwms.familyhealing.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fh_filedetails")
public class FHFileDetails {
    @Id
    @Getter
    @Setter
    @Column(name = "fhfiledetailsid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhFileDetailsIdGenerator",
            sequenceName = "fhFileDetailsIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhFileDetailsIdGenerator"
    )
    private Long fhFileDetailsId;

    @Getter
    @Setter
    @Column(name = "fileno")
    private Long fileNo;

    @Getter
    @Setter
    @Column(name = "clientname")
    private String clientName;

    @Getter
    @Setter
    @Column(name = "startdate")
    private LocalDate startDate;

    @Getter
    @Setter
    @Column(name = "enddate")
    private LocalDate endDate;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @Getter
    @Setter
    @Column(name = "community")
    private String community;

    @Getter
    @Setter
    @Column(name = "caseworker")
    private String caseworker;

    @Getter
    @Setter
    @Column(name = "department")
    private String department;

    @Getter
    @Setter
    @Column(name = "date")
    private LocalDate date;

    @Getter
    @Setter
    @Column(name = "reason")
    private String reason;

    @Getter
    @Setter
    @Column(name = "notes")
    @Lob
    private String notes;

    @Getter
    @Setter
    @Column(name = "statusofdeletion")
    private String statusOfDeletion;

    @CreationTimestamp
    @Getter
    @Setter
    @Column(name = "creationdatetime", updatable = false)
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Getter
    @Setter
    @Column(name = "lastwritten")
    private LocalDateTime lastWritten;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter
    @Setter
    private FHReferral fhReferral;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter
    @Setter
    private FHHistory fhHistory;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter
    @Setter
    private FHApproval fhApproval;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter @Setter
    private List<FHNeedAssessment> fhNeedAssessment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter @Setter
    private List<FHCasePlan> fhCasePlans;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter @Setter
    private List<FHSafetyPlan> fhSafetyPlans;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter @Setter
    private List<FHConsentForms> fhConsentForms;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fhfiledetailsid", referencedColumnName = "fhfiledetailsid")
    @Getter @Setter
    private List<FHProgressReport> fhProgressReport;
}
