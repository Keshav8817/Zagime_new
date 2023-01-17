package org.cyfwms.familyhealing.entity;

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
@Builder
@Entity
@Table(name = "fh_history")
public class FHHistory implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "fhhistoryid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "fhHistoryIdGenerator",
            sequenceName = "fhHistoryIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fhHistoryIdGenerator"
    )
    private Long fhHistoryId;

    @Getter
    @Setter
    @Lob
    @Column(name = "emotional")
    private String emotional;

    @Getter
    @Setter
    @Lob
    @Column(name = "family")
    private String family;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Getter
    @Setter
    @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter
    @Setter
    @Column(name = "lastwritten")
    private LocalDateTime lastwritten;

    @Getter
    @Setter
    @Column(name = "fhfiledetailsid")
    private Long fhFileDetailsId;
}
