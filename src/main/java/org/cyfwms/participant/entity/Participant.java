package org.cyfwms.participant.entity;

import lombok.*;
import org.cyfwms.common.entity.Appointments;
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
@Builder
@Entity
@Table(name = "participant")

public class Participant implements Serializable {
    @Id @Getter @Setter
    @Column(name = "participantid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "participantIdGenerator",
            sequenceName = "participantIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participantIdGenerator"
    )
    private Long participantId;

    @Getter @Setter @Column(name = "firstname")
    private String firstname;
    @Getter @Setter @Column(name = "surname")
    private String surname;
    @Getter @Setter @Column(name = "middlename")
    private String middleName;
    @Getter @Setter @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Getter @Setter @Column(name = "gender")
    private String gender;
    @Getter @Setter @Column(name = "maritalstatus")
    private String maritalStatus;
    @Getter @Setter @Column(name = "type",updatable = false)
    private String type;
    @Getter @Setter @Column(name = "status")
    private String status;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate", updatable = false)
    private LocalDateTime creationDateTime;
    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
    @Getter @Setter @Column(name = "isprimary")
    private boolean isPrimary;


    @Getter @Setter @Column(name = "referenceid", updatable = false, nullable = false)
    private Long referenceId;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private ParticipantContact participantContact;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private List<HouseholdMember> householdMemberList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private List<CounselorCFSWorker> counselorCFSWorkerList;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private CriminalHistory criminalHistory;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private Education education;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private List<ParticipantAttachment> participantAttachmentList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private List<ParticipantAppointment> participantAppointments;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private Employment employment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private List<FamilyPhysician> familyPhysicianList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private ParticipantOtherInformation participantOtherInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participantid", referencedColumnName = "participantid")
    @Getter @Setter
    private List<ParticipantReminder> participantReminderList;

}
