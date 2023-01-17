package org.cyfwms.common.login.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class  Users implements Serializable {
    @Id @Getter @Setter
    @Column(name = "usersid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "usersIdGenerator",
            sequenceName = "usersIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usersIdGenerator"
    )
    private Long usersId;
    @Getter @Setter @Column(name = "firstname")
    private String firstname;
    @Getter @Setter @Column(name = "surname")
    private String surname;
    @Getter @Setter @Column(name = "middlename")
    private String middleName;
    @Getter @Setter @Column(name = "username", nullable = false)
    private String username;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "password")
    private String password;
    @Getter @Setter @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;
    @Getter @Setter @Column(name = "creationdate")
    private LocalDate creationDate;
    @Getter @Setter @Column(name = "lastaccessdate")
    private LocalDate lastAccessDate;
    @Getter @Setter @Column(name = "updatedat")
    private LocalDate updatedAt;
    @Getter @Setter @Column(name = "title")
    private String title;
    @Getter @Setter @Column(name = "status")
    private String status;
    @Getter @Setter @Column(name = "phone")
    private String phone;
    @Getter @Setter @Column(name = "location")
    private String location;
    @Getter @Setter @Column(name = "preferredlanguage")
    private String preferredLanguage;
    @Getter @Setter @Column(name = "enabled")
    private boolean isEnabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "usersroles",
            joinColumns = @JoinColumn(name = "usersid"),
            inverseJoinColumns = @JoinColumn(name = "rolesid")
    )
    private Set<Roles> roles = new HashSet<>();

}
