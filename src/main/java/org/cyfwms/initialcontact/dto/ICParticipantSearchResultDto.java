package org.cyfwms.initialcontact.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ICParticipantSearchResultDto {
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

    public ICParticipantSearchResultDto(Long icParticipantId, Long fileDetailsId, String participant, String role, String notes) {
        this.icParticipantId = icParticipantId;
        this.fileDetailsId = fileDetailsId;
        this.participant = participant;
        this.role = role;
        this.notes = notes;
    }
}
