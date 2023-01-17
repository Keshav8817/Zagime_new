package org.cyfwms.participant.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "criminalhistoryrecord")
public class CriminalHistoryRecord implements Serializable {
    @Id @Column(name = "criminalhistoryrecordid", updatable = false, nullable = false)
    @Getter @Setter
    @SequenceGenerator(
            name = "criminalHistoryRecordIdGenerator",
            sequenceName = "criminalHistoryRecordIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "criminalHistoryRecordIdGenerator"
    )
    private Long criminalHistoryRecordId;
    @Getter @Setter @Column(name = "arrestdate")
    private LocalDate arrestDate;
    @Getter @Setter @Column(name = "charges")
    private String charges;
    @Getter @Setter @Column(name = "conviction")
    private String conviction;
    @Getter @Setter @Column(name = "sentence")
    private String sentence;
    @Getter @Setter @Column(name = "status")
    private String status;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate",updatable = false)
    private LocalDate creationDate;
    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
    @Getter @Setter @Column(name = "criminalhistoryid",updatable = false)
    private Long criminalHistoryId;
}
