package org.cyfwms.common.login.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles implements Serializable {
    @Id
    @Getter
    @Setter
    @Column(name = "rolesid", updatable = false, nullable = false)
    @SequenceGenerator(
            name = "rolesIdGenerator",
            sequenceName = "rolesIdGenerator",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rolesIdGenerator"
    )
    private Long rolesId;
    @Getter @Setter @Column(name = "name", nullable = false)
    private String name;
    @Getter @Setter @Column(name = "status")
    private String status;
}
