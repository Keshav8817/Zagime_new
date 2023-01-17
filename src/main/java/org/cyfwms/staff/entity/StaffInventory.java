package org.cyfwms.staff.entity;

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
@Table(name = "staff_inventory")
public class StaffInventory implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "staffinventoryid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "staffInventoryIdGenerator",
            sequenceName = "staffInventoryIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staffInventoryIdGenerator"
    )
    private Long staffInventoryId;


    @Getter @Setter @Column(name = "item")
    private String item;

    @Getter @Setter @Column(name = "serialno")
    private Long serialNo;

    @Getter @Setter @Column(name = "fromdate")
    private LocalDate from;

    @Getter @Setter @Column(name = "todate")
    private LocalDate to;

    @Getter @Setter @Column(name = "inuse")
    private Boolean inUse;

    @Getter @Setter @Column(name = "notes")
    @Lob
    private String notes;

    @Getter @Setter @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;

    @Getter @Setter @Column(name = "staffid")
    private Long staffId;
}
