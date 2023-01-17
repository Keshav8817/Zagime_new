package org.cyfwms.initialcontact.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ic_contactnotes")
public class ICContactNotes implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "contactnotesid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "contactnotesidgenerator",
            sequenceName = "contactnotesidgenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contactnotesidgenerator"
    )
    private Long contactNotesId;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "worker")
    private String worker;

    @Getter @Setter @Column(name = "date")
    private LocalDate date;

    @Getter @Setter @Column(name = "time")
    private LocalTime time;

    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdatetime")
    private LocalDateTime creationDateTime;

    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastWritten;

    @Getter @Setter @Column(name = "contactmethod")
    private String contactMethod;

    @Getter @Setter @Column(length = 1000,name = "needaddress")
    private String needAddress;

    @Getter @Setter @Column(length = 1000,name = "summary")
    private String summary;

    @Getter @Setter @Column(length = 1000,name = "result")
    private String result;

    @Getter @Setter @Column(length = 1000,name = "nextstep")
    private String nextStep;

    @Getter @Setter @Column(length = 1000,name = "caseplanprogress")
    private String casePlanProgress;

    @Getter @Setter @Column(length = 1000,name = "additionalinformation")
    private String additionalInformation;

    @Getter @Setter @Column(name = "status")
    private String status;

    @Getter @Setter @Column(name = "filedetailsid")
    private Long fileDetailsId;

}
