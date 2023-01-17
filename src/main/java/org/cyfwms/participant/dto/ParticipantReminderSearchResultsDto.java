package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor

public class ParticipantReminderSearchResultsDto {

    @Getter
    @Setter
    private Long reminderId;

    @Getter
    @Setter
    private Long participantId;

    @Getter
    @Setter
    private String assignedTo;

    @Getter
    @Setter
    private String regarding;

    @Getter
    @Setter
    private String subject;
    @Getter
    @Setter
    private String frequency;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private LocalDate reminderDate;
    @Getter
    @Setter
    private LocalDate endDate;

    @Getter
    @Setter
    private Long participantReminderId;

    public ParticipantReminderSearchResultsDto(Long reminderId, Long participantId, String assignedTo, String regarding, String subject, String frequency, String status, String description, LocalDate reminderDate, LocalDate endDate,Long participantReminderId) {
        this.reminderId = reminderId;
        this.participantId = participantId;
        this.assignedTo = assignedTo;
        this.regarding = regarding;
        this.subject = subject;
        this.frequency = frequency;
        this.status = status;
        this.description = description;
        this.reminderDate = reminderDate;
        this.endDate = endDate;
        this.participantReminderId=participantReminderId;
    }
}
