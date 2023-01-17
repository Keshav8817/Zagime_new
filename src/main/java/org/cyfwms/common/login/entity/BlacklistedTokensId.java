package org.cyfwms.common.login.entity;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistedTokensId implements Serializable {
    @Getter @Setter @Column(name = "username")
    private String username;
    @Getter @Setter @Column(name = "jwttoken")
    private String jwtToken;
}
