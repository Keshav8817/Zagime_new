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
@Table(name = "participantotherinformation")
public class ParticipantOtherInformation implements Serializable {
    @Id @Column(name = "participantotherinfoid", updatable = false, nullable = false)
    @Getter @Setter
    @SequenceGenerator(
            name = "participantOtherInfoIdGenerator",
            sequenceName = "participantOtherInfoIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participantOtherInfoIdGenerator"
    )
    private Long participantOtherInfoId;
    @Getter @Setter @Column(length = 1000,name = "strength")
    private String strength;
    @Getter @Setter @Column(length = 1000,name = "weakness")
    private String weakness;
    @Getter @Setter @Column(length = 1000,name = "skills")
    private String skills;
    @Getter @Setter @Column(length = 1000,name = "experiences")
    private String experiences;
    @Getter @Setter @Column(length = 1000,name = "effectivecopingskills")
    private String effectiveCopingSkills;
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
