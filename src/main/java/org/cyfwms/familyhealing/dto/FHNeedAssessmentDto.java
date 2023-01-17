package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHNeedAssessmentDto {
    @Getter
    @Setter
    private Long fhNeedAssessmentId = 0L;

    @Getter
    @Setter
    private LocalDate assessmentDate;

    @Getter
    @Setter
    private String completedBy;

    @Getter
    @Setter
    private String family;

    @Getter
    @Setter
    private String identified;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String resources;

    @Getter
    @Setter
    private Long fhFileDetailsId = 0L;
}
