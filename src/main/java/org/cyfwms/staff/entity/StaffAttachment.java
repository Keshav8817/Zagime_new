package org.cyfwms.staff.entity;

import lombok.*;
import org.cyfwms.common.entity.Attachment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="staff_attachments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffAttachment {
    @Id
    @Column(name="staffattachmentid", nullable=false)
    @SequenceGenerator(name="staffAttachmentIdGenerator", sequenceName="staffAttachmentIdGenerator", allocationSize=100)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="staffAttachmentIdGenerator")
    private Long staffAttachmentId;

    @Column(name="name")
    private String name;

    @CreationTimestamp
    @Column(name="creationdatetime")
    private LocalDateTime creationDateTime;


    @Column(name="type")
    private String type;

    @Column(name = "staffid")
    private Long staffId;


    @Column(name ="status")
    private  String status;


    @Column(name="lastWritten")
    @UpdateTimestamp
    private LocalDateTime lastWritten;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachmentId", referencedColumnName = "attachmentId")
    @Getter
    @Setter
    private Attachment attachment;
}
