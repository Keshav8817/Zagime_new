package org.cyfwms.participant.entity;

import lombok.*;
import org.cyfwms.common.entity.Reminder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participantreminder")
public class ParticipantReminder implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "participantreminderid", updatable = false, nullable = false)
    @SequenceGenerator(name = "participantReminderIdGenerator", sequenceName = "participantReminderIdGenerator", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "participantReminderIdGenerator")
    private Long participantReminderId;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reminderid", referencedColumnName = "reminderid")
    private Reminder reminder;


    @CreationTimestamp
    @Getter
    @Setter
    @Column(name = "creationdatetime")
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Getter
    @Setter
    @Column(name = "lastwritten")
    private LocalDateTime lastWritten;

    @Getter
    @Setter
    @Column(name = "statusofdeletion")
    private String statusOfDeletion;

    @Getter
    @Setter
    @Column(name = "participantid")
    private Long participantId;

}
