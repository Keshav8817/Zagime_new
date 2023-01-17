package org.cyfwms.initialcontact.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ic_filedetails")
public class ICFileDetails implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "filedetailsid", updatable = false, nullable = false)
    @SequenceGenerator(name = "filedetailsidgenerator", sequenceName = "filedetailsidgenerator", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filedetailsidgenerator")
    private Long fileDetailsId;

    @Getter
    @Setter
    @Column(name = "clientname")
    private String clientName;

    @Getter
    @Setter
    @Column(name = "caseworker")
    private String caseworker;

    @Getter
    @Setter
    @Column(name = "fileNumber", updatable = false)
    private Long fileNumber;

    @Getter
    @Setter
    @Column(name = "startingdate")
    private LocalDate startingDate;

    @Getter
    @Setter
    @Column(name = "dateclosed")
    private LocalDate dateClosed;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @Getter
    @Setter
    @Column(name = "statusofdeletion")
    private String statusOfDeletion;

    @CreationTimestamp
    @Getter
    @Setter
    @Column(name = "creationdate")
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Getter
    @Setter
    @Column(name = "lastwritten")
    private LocalDateTime lastwritten;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "filedetailsid", referencedColumnName = "filedetailsid")
    @Getter
    @Setter
    private ICIncidentReport incidentReport;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "filedetailsid", referencedColumnName = "filedetailsid")
    @Getter
    @Setter
    private ICPatientCareInfo patientCareInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "filedetailsid", referencedColumnName = "filedetailsid")
    @Getter
    @Setter
    private ICPresentConcerns presentConcerns;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "filedetailsid", referencedColumnName = "filedetailsid")
    @Getter
    @Setter
    private ICReferralInfo referralInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "filedetailsid", referencedColumnName = "filedetailsid")
    @Getter
    @Setter
    private List<ICAppointment> icAppointmentList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "filedetailsid", referencedColumnName = "filedetailsid")
    @Getter
    @Setter
    private List<ICReminder> icReminderList;
}
