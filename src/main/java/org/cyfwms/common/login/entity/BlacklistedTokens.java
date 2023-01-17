package org.cyfwms.common.login.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BlacklistedTokens")
@IdClass(BlacklistedTokensId.class)
public class BlacklistedTokens implements Serializable {
    @Id @Getter @Setter @Column(name = "username")
    private String username;
    @Id @Getter @Setter @Column(name = "jwttoken")
    private String jwtToken;
    @Getter @Setter @Column(name = "blacklisteddate")
    private LocalDate blacklistedDate;


}
