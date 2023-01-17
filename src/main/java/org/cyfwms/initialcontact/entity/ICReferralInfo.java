package org.cyfwms.initialcontact.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ic_referralinfo")
public class ICReferralInfo implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "referralinfoid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "referralinfoidgenerator",
            sequenceName = "referralinfoidgenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "referralinfoidgenerator"
    )
    private Long referralInfoId;

    @Getter @Setter @Column(name = "referral")
    private String referral;

    @Getter @Setter @Column(name = "selfReferred")
    private Boolean selfReferred;

    @Getter @Setter @Column(name = "agencyname")
    private String agencyName;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "address")
    private String address;

    @Getter @Setter @Column(name = "phone")
    private String phone;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "filedetailsid")
    private Long fileDetailsId;

    @Getter @Setter @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastWritten;
}
