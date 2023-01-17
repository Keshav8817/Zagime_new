package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class EducationAndEmploymentCompositeDto {
    @Getter
    @Setter
    private Long educationId=0L;

    @Getter
    @Setter
    private String attendingSchool;

    @Getter
    @Setter
    private String school;

    @Getter
    @Setter
    private String grade;

    @Getter
    @Setter
    private Long participantId;

    @Getter
    @Setter
    private Long employmentId=0L;

    @Getter
    @Setter
    private String employed;

    @Getter
    @Setter
    private String typeOfEmployment;

    @Getter
    @Setter
    private String desiredProfession;
}
