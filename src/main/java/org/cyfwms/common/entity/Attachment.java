package org.cyfwms.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="attachment")
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @Id
    @Column(name="attachmentid", nullable = false )
    @Getter
    @Setter
    @SequenceGenerator(
            name = "attachmentIdGenerator",
            sequenceName = "attachmentIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attachmentIdGenerator"
    )
    private Long attachmentId;
    @Lob
    @Getter @Setter @Column(name = "attachmentContents")
    private byte[] attachmentContents;
    @Getter @Setter @Column(name = "attachmentName")
    private String attachmentName;
    @Getter @Setter @Column(name = "attachmentStatus")
    private String attachmentStatus;
    @Getter @Setter @Column(name = "fileLocation")
    private String fileLocation;
    @Getter @Setter @Column(name = "fileReference")
    private String fileReference;
    @Getter @Setter @Column(name = "documentType")
    private String documentType;
    @CreationTimestamp
    @Getter @Setter @Column(name = "receiptDate")
    private LocalDateTime receiptDate;
    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
}
