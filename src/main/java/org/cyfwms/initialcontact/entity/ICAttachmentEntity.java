package org.cyfwms.initialcontact.entity;

import lombok.*;
import org.cyfwms.common.entity.Attachment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="ic_attachments")
public class ICAttachmentEntity {
    @Id
    @Column(name="icattchmentid", nullable=false)
    @SequenceGenerator(name="icAttachmentIdGenerator", sequenceName="icAttachmentIdGenerator", allocationSize=100)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="icAttachmentIdGenerator")
    private Long icAttachmentId;

    private String name;


    private String type;

    @Column(name = "filedetailsid")
    private Long fileDetailsId;


    private  String status;

    @Column(name="creationdatetime")
    @CreationTimestamp
    private LocalDateTime creationDateTime;


    @Column(name="lastwritten")
    @UpdateTimestamp
    private LocalDateTime lastWritten;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachmentid", referencedColumnName = "attachmentid")
    @Getter
    @Setter
    private Attachment attachment;
}
