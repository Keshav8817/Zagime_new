package org.cyfwms.staff.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training")
public class Training {
    @Id
    @Getter
    @Setter
    @Column(name = "trainingid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "trainingIdGenerator",
            sequenceName = "trainingIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trainingIdGenerator"
    )
    private Long trainingId;
    @Getter @Setter @Column(name = "trainingname")
    private String trainingName;

    @Getter @Setter @Column(name = "status")
    private String status;

    @Getter @Setter @Column(name = "date_of_training")
    private LocalDate dateOfTraining;

    @Getter @Setter @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Lob
    @Getter @Setter @Column(name = "notes")
    private String notes;

    @Getter @Setter @Column(name = "staffid")
    private Long staffId;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdatetime", updatable = false)
    private LocalDateTime creationDateTime;

    @Getter @Setter @Column(name = "status_of_deletion")
    private  String statusOfDeletion;

}
