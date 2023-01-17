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
@Table(name = "staff_jobandbanking")
public class StaffJobAndBanking {
    @Id
    @Getter
    @Setter
    @Column(name = "staffjobandbankingid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "staffJobAndBankingIdGenerator",
            sequenceName = "staffJobAndBankingIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "staffJobAndBankingIdGenerator"
    )
    private Long staffJobAndBankingId;


    @Getter @Setter @Column(name = "active")
    private boolean active;

    @Getter @Setter @Column(name = "startdate")
    private LocalDate startDate;

    @Getter @Setter @Column(name = "enddate")
    private LocalDate endDate;

    @Getter @Setter @Column(name = "workphone")
    private String workPhone;

    @Getter @Setter @Column(name = "workemail")
    private String workEmail;

    @Getter @Setter @Column(name = "jobtitle")
    private String jobTitle;

    @Getter @Setter @Column(name = "salary")
    private Long salary;


    @Getter @Setter @Column(name = "bankaccount")
    private String bankAccount;

    @Getter @Setter @Column(name = "transit")
    private String transit;

    @Getter @Setter @Column(name = "bank")
    private String bank;

    @Getter @Setter @Column(name = "bankname")
    private String bankName;

    @Getter @Setter @Column(name = "bankdddress")
    @Lob
    private String bankAddress;

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
