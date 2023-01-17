package org.cyfwms.initialcontact.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patientcareinfoinpatient")
public class PatientCareInfoInpatient {
    @Id
    @Getter
    @Setter
    @Column(name = "inpatientid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "inpatientidgenerator",
            sequenceName = "inpatientidgenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inpatientidgenerator"
    )
    private Long inpatientId;

    @Getter @Setter @Column(name = "hospitalizationrecord")
    private String hospitalizationRecord;

    @Getter @Setter @Column(length = 1000,name = "hospitalizationreasons")
    private String hospitalizationReasons;

    @Getter @Setter @Column(name = "patientcareinfoid")
    private Long patientCareInfoId;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate",updatable = false)
    private LocalDate creationDate;

    @UpdateTimestamp
    private LocalDateTime lastwritten;

    @Getter @Setter @Column(name = "status")
    private String status;
}
