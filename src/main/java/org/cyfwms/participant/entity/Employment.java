package org.cyfwms.participant.entity;

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
@Entity
@Table(name = "employment")
public class Employment implements Serializable {
    @Id @Column(name = "employmentid", updatable = false, nullable = false)
    @Getter @Setter
    @SequenceGenerator(
            name = "employmentIdGenerator",
            sequenceName = "employmentIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employmentIdGenerator"
    )
    private Long employmentId;

    @Getter @Setter @Column(name = "employed")
    private String employed;
    @Getter @Setter @Column(name = "typeofemployment")
    private String typeOfEmployment;
    @Getter @Setter @Column(name = "desiredprofession")
    private String desiredProfession;
    @Getter @Setter @Column(name = "status")
    private String status;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;
    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
    @Getter @Setter @Column(name = "participantid")
    private Long participantId;
}
