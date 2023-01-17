package org.cyfwms.staff.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class GoalsAndObjectivesDto {
    @Getter
    @Setter
    private Long goalsAndObjectivesId;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
        private String status;

    @Getter
    @Setter
    private String supervisor;

    @Getter
    @Setter
    private String planPeriod;

    @Getter
    @Setter
    private String goalsAndObjectives;

    @Getter
    @Setter
    private String guidesAndEnablers;

    @Getter
    @Setter
    private String reviewComments;

    @Getter
    @Setter
    private String reviewedBy;

    @Getter
    @Setter
    private LocalDate reviewDate;

    @Getter
    @Setter
    private Long staffId;

    @Getter
    @Setter
    private Long supervisorId;
}
