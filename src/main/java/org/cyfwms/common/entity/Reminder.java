package org.cyfwms.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cyfwms.initialcontact.entity.ICReminder;
import org.cyfwms.participant.entity.ParticipantReminder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="reminder")
@AllArgsConstructor
@NoArgsConstructor
public class Reminder implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "reminderid",updatable = false,nullable = false)
    @SequenceGenerator(name = "reminderIdGenerator",sequenceName = "reminderIdGenerator",allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reminderIdGenerator")
    private Long reminderId;
    @Getter @Setter @Column(name = "reminderdate")
    private LocalDate reminderDate;
    @Getter @Setter @Column(name = "assignedto")
    private String assignedTo;
    @Getter @Setter @Column(name = "regarding")
    private String regarding;
    @Getter @Setter @Column(name = "subject")
    private String subject;
    @Getter @Setter @Column(name = "status")
    private String status;
    @Getter @Setter @Column(name = "description")
    @Lob
    private String description;
    @Getter @Setter @Column(name = "frequency")
    private String frequency;
    @Getter @Setter @Column(name = "enddate")
    private LocalDate endDate;
    @Getter@Setter@Column(name = "statusofdeletion")
    private String statusOfDeletion;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdatetime")
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastWritten;

    @Getter @Setter
    @OneToOne(mappedBy = "reminder")
    private ParticipantReminder participantReminder;

    @Getter @Setter
    @OneToOne(mappedBy = "reminder")
    private ICReminder iCReminder;


}
