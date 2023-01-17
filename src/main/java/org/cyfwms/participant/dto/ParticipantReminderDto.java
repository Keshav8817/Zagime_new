package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.cyfwms.common.dto.ReminderDto;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantReminderDto {

    @Getter
    @Setter
    private Long participantReminderId;

    @Getter
    @Setter
    private Long participantId;

    @Getter
    @Setter
    private ReminderDto reminderDto;

    @Getter
    @Setter
    private Long referenceId;

}
