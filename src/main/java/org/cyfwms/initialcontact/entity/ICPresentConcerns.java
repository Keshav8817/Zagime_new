package org.cyfwms.initialcontact.entity;

import java.io.Serializable;
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
@Table(name = "ic_presentconcerns")
public class ICPresentConcerns implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "presentconcernsid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "presentconcernsidgenerator",
            sequenceName = "presentconcernsidgenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "presentconcernsidgenerator"
    )
    private Long presentConcernsId;

    @Getter @Setter @Column(name = "selectpresentconcerns")
    private String selectPresentConcerns;

    @Getter @Setter @Column(length = 1000,name = "situation")
    private String situation;

    @Getter @Setter @Column(name = "substanceabuse")
    private String substanceAbuse;

    @Getter @Setter @Column(length = 1000,name = "explainmentalhealth")
    private String explainMentalHealth;

    @Getter @Setter @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastWritten;

    @Getter @Setter @Column(name = "filedetailsid")
    private Long fileDetailsId;
}
