package org.cyfwms.culturalprogram.entity;

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
@Table(name="cpa_attachments")
public class AttachmentEntity {
  @Id
  @Column(name="culturalprogimageid", nullable=false)
  @SequenceGenerator(name="culturalprogAttachmentIdGenerator", sequenceName="culturalprogAttachmentIdGenerator", allocationSize=100)
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="culturalprogAttachmentIdGenerator")
  private Long culturalProgAttachmentId;

  private String name;


  @Column(name="creationdatetime")
  @CreationTimestamp
  private LocalDateTime creationDateTime;

  private String type;

  @Column(name = "culturalprogramid")
  private Long culturalProgramId;


  private  String status;


  @Column(name="lastwritten")
  @UpdateTimestamp
  private LocalDateTime lastWritten;


  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "attachmentid", referencedColumnName = "attachmentid")
  @Getter @Setter
  private Attachment attachment;
}
