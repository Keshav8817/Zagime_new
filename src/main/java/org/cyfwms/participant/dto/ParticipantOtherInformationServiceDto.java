package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantOtherInformationServiceDto {
    @Getter
    @Setter
    private Long participantOtherInfoId=0L;

    @Getter
    @Setter
    private Long participantId;

    @Getter
    @Setter
    private String strength;

    @Getter
    @Setter
    private String weakness;

    @Getter
    @Setter
    private String skills;

    @Getter
    @Setter
    private String experiences;

    @Getter
    @Setter
    private String effectiveCopingSkills;
}
