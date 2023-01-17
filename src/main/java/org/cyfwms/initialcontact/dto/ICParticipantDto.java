package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ICParticipantDto {
    @Getter
    @Setter
    private Long icParticipantId;
    @Getter @Setter
    private Long fileDetailsId;
    @Getter @Setter
    private String participant;
    @Getter @Setter
    private String role;
    @Getter @Setter
    private String notes;
    @Getter @Setter
    private Long participantId;
}
