package org.cyfwms.common.login.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {
    @Getter @Setter
    //@NotNull(message = "First Name cannot be null")
    private String username;
    @Getter @Setter
    private String password;
}
