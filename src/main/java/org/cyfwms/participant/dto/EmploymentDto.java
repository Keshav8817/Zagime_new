package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentDto {
    @Getter @Setter
    private Long employmentId=0L;
    @Getter @Setter
    private String employed;
    @Getter @Setter
    private String typeOfEmployment;
    @Getter @Setter
    private String desiredProfession;
    @Getter @Setter
    private Long participantId;
    @Getter @Setter
    private String status;
}
