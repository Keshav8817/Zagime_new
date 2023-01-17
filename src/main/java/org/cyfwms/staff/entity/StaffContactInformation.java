package org.cyfwms.staff.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff_contactinformation")
public class StaffContactInformation {
    @Id
    @Getter
    @Setter
    @Column(name = "staffContactinformationid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "staffContactInformationIdGenerator",
            sequenceName = "staffContactInformationIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staffContactInformationIdGenerator"
    )
    private Long staffContactInformationId;
    @Getter @Setter @Column(name = "addressline1")

    private String addressLine1;

    @Getter @Setter @Column(name = "addressline2")
    private String addressLine2;

    @Getter @Setter @Column(name = "city")
    private String city;

    @Getter @Setter @Column(name = "province")
    private String province;

    @Getter @Setter @Column(name = "postalcode")
    private String postalCode;

    @Getter @Setter @Column(name = "emailaddress")
    private String emailAddress;

    @Getter @Setter @Column(name = "cellphone")
    private String cellPhone;

    @Getter @Setter @Column(name = "homephone")
    private String homePhone;

    @Getter @Setter @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastWritten;

    @Getter @Setter @Column(name = "staffid")
    private Long staffId;
}
