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
@Table(name = "householdmember")
public class HouseholdMember implements Serializable {
    @Id @Column(name = "householdmemberid", updatable = false, nullable = false)
    @Getter @Setter
    @SequenceGenerator(
            name = "householdMemberIdGenerator",
            sequenceName = "householdMemberIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "householdMemberIdGenerator"
    )
    private Long householdMemberId;
    @Getter @Setter @Column(name = "name")
    private String name;
    @Getter @Setter @Column(name = "gender")
    private String gender;
    @Getter @Setter @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Getter @Setter @Column(name = "relationship")
    private String relationship;
    @Getter @Setter @Column(name = "residing")
    private String residing;
    @Getter @Setter @Column(name = "status")
    private String status;
    @CreationTimestamp
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;
    @UpdateTimestamp
    @Getter @Setter @Column(name = "lastwritten")
    private LocalDateTime lastwritten;
    @Getter @Setter @Column(name = "participantid")
    private Long participantId;
}
