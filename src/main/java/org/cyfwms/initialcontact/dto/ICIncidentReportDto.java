package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICIncidentReportDto {
    @Getter
    @Setter
    private Long fileDetailsId;

    @Getter
    @Setter
    private Long incidentReportId=0L;

    @Getter
    @Setter
    private LocalDate dateOfReport;

    @Getter
    @Setter
    private String reportedBy;

    @Getter
    @Setter
    private String partiesInvolved;

    @Getter
    @Setter
    private String witnesses;

    @Getter
    @Setter
    private LocalDate incidentDate;

    @Getter
    @Setter
    private LocalTime incidentTime;

    @Getter
    @Setter
    private String incidentLocation;

    @Getter
    @Setter
    private String risk;

    @Getter
    @Setter
    private String actionTaken;

    @Getter
    @Setter
    private String actionPlan;
}
