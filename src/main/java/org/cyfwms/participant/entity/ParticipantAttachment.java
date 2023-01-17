package org.cyfwms.participant.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cyfwms.common.entity.Attachment;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="participantattachment")
@AllArgsConstructor
@NoArgsConstructor

public class ParticipantAttachment {
    @Id
    @Column(name="participantattachmentid", nullable = false )
    @Getter@Setter
    @SequenceGenerator(
            name = "participantAttachmentIdGenerator",
            sequenceName = "participantAttachmentIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participantAttachmentIdGenerator"
    )
    private Long participantAttachmentId;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "type")
    private String type;

    @Getter @Setter @Column(name = "participantid")
    private Long participantId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachmentid", referencedColumnName = "attachmentid",updatable = false)
    @Getter @Setter
    private Attachment attachment;

    @Getter @Setter @Column(name = "attachmenttype")
    private String attachmentType;

    @Getter @Setter @Column(name = "status")
    private String status;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
}
