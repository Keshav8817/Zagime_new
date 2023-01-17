package org.cyfwms.staff.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "background_check")
public class BackgroundCheck {
    @Id
    @Getter
    @Setter
    @Column(name = "backgroundcheckid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "backgroundcheckIdGenerator",
            sequenceName = "backgroundcheckIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "backgroundcheckIdGenerator"
    )
    private Long backgrounCheckId;

    @Getter @Setter @Column(name = "type_of_check")
    private String typeOfCheck;

    @Getter @Setter @Column(name = "status")
    private String status;

    @Getter @Setter @Column(name = "date_requested")
    private LocalDate dateRequested;

    @Getter @Setter @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @Getter @Setter @Column(name = "requested_by")
    private String requestedBy;

    @Getter @Setter @Column(name = "staffid")
    private Long staffId;

    @Lob
    @Getter @Setter @Column(name="notes")
    private  String notes;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdatetime", updatable = false)
    private LocalDateTime creationDateTime;

    @Getter @Setter @Column(name = "status_of_deletion")
    private  String statusOfDeletion;




}
