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
@Table(name = "fh_approval")
public class FHApproval implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "fhapprovalid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhApprovalIdGenerator",
            sequenceName = "fhApprovalIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhApprovalIdGenerator"
    )
    private Long fhApprovalId;

    @Getter
    @Setter
    @Column(name = "submittedby")
    private String submittedBy;

    @Getter
    @Setter
    @Column(name = "submitteddate")
    private LocalDate submittedDate;

    @Getter
    @Setter
    @Column(name = "approvedby")
    private String approvedBy;

    @Getter
    @Setter
    @Column(name = "approveddate")
    private LocalDate approvedDate;

    @Getter
    @Setter
    @Column(name = "comments")
    @Lob
    private String comments;

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
