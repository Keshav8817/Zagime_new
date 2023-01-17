package org.cyfwms.staff.entity;

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
@Table(name = "staff")
public class Staff {
    @Id
    @Getter
    @Setter
    @Column(name = "staffid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "staffIdIdGenerator",
            sequenceName = "staffIdIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staffIdGenerator"
    )
    private Long staffId;

    @Getter
    @Setter
    @Column(name = "firstname")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "middlename")
    private String middleName;

    @Getter
    @Setter
    @Column(name = "lastname")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Getter
    @Setter
    @Column(name = "department")
    private String department;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @Getter
    @Setter
    @Column(name = "worklocation")
    private String workLocation;

    @Getter
    @Setter
    @Column(name = "supervisor")
    private String supervisor;

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

    @Getter
    @Setter
    @Column(name = "employeeid")
    private String employeeId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter
    @Setter
    private StaffContactInformation staffContactInformation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter
    @Setter
    private StaffJobAndBanking staffJobAndBanking;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter
    @Setter
    private StaffMedicalAndEmergency staffMedicalAndEmergency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter
    @Setter
    private List<StaffInventory> staffInventoryList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter @Setter
    private List<Training> training;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter @Setter
    private List<StaffGoalsAndObjectives> staffGoalsAndObjectivesList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter @Setter
    private List<StaffAttachment> staffAttachmentList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "staffid")
    @Getter @Setter
    private List<BackgroundCheck> backgroundChecks;


}
