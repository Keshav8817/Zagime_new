package org.cyfwms.initialcontact.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ic_participant")
public class ICParticipant {
    @Id
    @Getter
    @Setter
    @Column(name = "icparticipantid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "icparticipantidgenerator",
            sequenceName = "icparticipantidgenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "icparticipantidgenerator"
    )
    private Long icParticipantId;
    @Getter @Setter @Column(name = "role")
    private String role ;
    @Getter @Setter @Column(name = "notes",length = 1000)
    private String notes;
    @Getter @Setter @Column(name = "status")
    private String status;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdatetime")
    private LocalDateTime creationDateTime;
    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastWritten;
    @Getter @Setter @Column(name = "participantid")
    private String  participant;
    @Getter @Setter @Column(name = "filedetailsid")
    private Long fileDetailsId;
}
