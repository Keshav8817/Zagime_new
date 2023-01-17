package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantReminderSearchCriteriaDto {

    @Getter
    @Setter
    private Long participantId;
    @Getter
    @Setter
    private String data;

}
