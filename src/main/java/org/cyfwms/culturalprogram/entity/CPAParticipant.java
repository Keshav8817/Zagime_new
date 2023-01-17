package org.cyfwms.culturalprogram.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participantculturalprogram")
public class CPAParticipant implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "participantculturalprogid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "participantCulturalProgIdGenerator",
            sequenceName = "participantCulturalProgIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participantCulturalProgIdGenerator"
    )
   private Long participantCulturalProId;
    @Getter @Setter @Column(name = "role")
    private String role ;
    @Getter @Setter @Column(name = "notes",length = 1000)
    private String notes;
    @Getter @Setter @Column(name = "status")
    private String status;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdatetime")
    private LocalDateTime creationDateTime;
    @Getter @Setter @Column(name = "participantid")
    private String  participant;
    @Getter @Setter @Column(name = "culturalprogramid")
    private Long culturalProgramId;
}
