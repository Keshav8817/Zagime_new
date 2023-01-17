package org.cyfwms.initialcontact.entity;

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
@Table(name = "ic_reminder")
public class ICReminder implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "icreminderid", updatable = false, nullable = false)
    @SequenceGenerator(name = "icreminderIdGenerator", sequenceName = "icreminderIdGenerator", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "icreminderIdGenerator")
    private Long icReminderId;

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


    @Column(name = "filedetailsid")
    @Getter
    @Setter
    private Long fileDetailsId;


}
