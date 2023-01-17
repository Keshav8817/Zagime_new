package org.cyfwms.initialcontact.entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "ic_patientcareinfo")
public class ICPatientCareInfo implements Serializable {
    @Column(name = "typeofpatient")
    @Getter
    @Setter
    private String typeOfPatient;

    @Getter
    @JoinColumn(name = "patientcareinfoid", referencedColumnName = "patientcareinfoid")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private PatientCareInfoInpatient inpatient;

    @Getter
    @JoinColumn(name = "patientcareinfoid", referencedColumnName = "patientcareinfoid")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private PatientCareInfoOutpatient outpatient;

    @Column(name = "patientcareinfoid", updatable = false, nullable = false)
    @Getter
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patientcareinfoidgenerator"
    )
    @SequenceGenerator(
            name = "patientcareinfoidgenerator",
            sequenceName = "patientcareinfoidgenerator",
            allocationSize = 100
    )
    @Setter
    private Long patientCareInfoId;

    @Column(name = "filedetailsid")
    @Getter
    @Setter
    private Long fileDetailsId;

    @Column(name = "status")
    @Getter
    @Setter
    private String status;

    @Column(name = "creationdate")
    @CreationTimestamp
    @Getter
    @Setter
    private LocalDate creationDate;

    @Column(name = "lastwritten")
    @UpdateTimestamp
    @Getter
    @Setter
    private LocalDateTime lastwritten;
}
