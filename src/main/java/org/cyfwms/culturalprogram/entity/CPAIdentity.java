package org.cyfwms.culturalprogram.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "culturalprogandact")
public class CPAIdentity implements Serializable {
    @Id @Getter @Setter
    @Column(name = "culturalprogramid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "culturalProgramIdGenerator",
            sequenceName = "culturalProgramIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "culturalProgramIdGenerator"
    )
    private Long culturalProgramId;
    @Getter @Setter @Column(name = "referenceid", updatable = false, nullable = false)
    private Long referenceId;
    @Getter @Setter @Column(name = "name")
    private String name;
    @Getter @Setter @Column(name = "type")
    private String type;
    @Getter @Setter @Column(name = "status")
    private String  status;
    @Getter @Setter @Column(name = "caseworker")
    private String  caseworker;
    @Getter @Setter @Column(name = "startdate")
    private LocalDate startDate;
    @Getter @Setter @Column(name = "enddate")
    private LocalDate endDate;
    @Getter @Setter @Column(name = "totalcost")
    private String totalCost;
    @Getter @Setter @Column(name = "totalparticipation")
    private String totalParticipation;
    @Getter @Setter @Column(name = "sessiondetails" ,length = 1000)
    private String sessionDetails;
    @Getter @Setter @Column(name = "costorparticipationdetails",length = 1000)
    private String costOrParticipationDetails;
    @Getter @Setter @Column(name = "outcomes" ,length = 1000)
    private String outcomes;
    @Getter @Setter @Column(name = "notes",length = 1000)
    private String notes;
    @Getter @Setter @Column(name = "statusofdeletion")
    private String statusOfDeletion;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate",updatable = false)
    private LocalDate creationDate;
    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastWritten;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "culturalprogramid", referencedColumnName = "culturalprogramid")
    @Getter @Setter
    private CPAParticipant CPAParticipant;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "culturalprogramid", referencedColumnName = "culturalprogramid")
    @Getter @Setter
    private List<AttachmentEntity> culturalProgAttachment;
}
