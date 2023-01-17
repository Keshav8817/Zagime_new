package org.cyfwms.culturalprogram.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CPAParticipantDto {
    @Getter @Setter
    private Long participantCulturalProId;
    @Getter @Setter
    private Long culturalProgramId;
    @Getter @Setter
    private String participant;
    @Getter @Setter
    private String role;
    @Getter @Setter
    private String notes;
    @Getter @Setter
    private Long participantId;
}
