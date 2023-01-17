package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class FHProgressReportDto {
    @Getter
    @Setter
    private Long fhProgressReportId;

    @Getter
    @Setter
    private LocalDate dateOfAssessment;

    @Getter
    @Setter
    private String completedBy;

    @Getter
    @Setter
    private String areas;

    @Getter
    @Setter
    private String sessionsAttended;

    @Getter
    @Setter
    private String sessions;

    @Getter
    @Setter
    private String progress;

    @Getter
    @Setter
    private Long fhFileDetailsId;
}
