package org.cyfwms.initialcontact.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.cyfwms.initialcontact.entity.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICCommonDataDto {

    @Getter
    @Setter
    private Long fileDetailsId;

    @Getter
    @Setter
    private String clientName;

    @Getter
    @Setter
    private String caseworker;

    @Getter
    @Setter
    private Long fileNumber;

    @Getter
    @Setter
    private LocalDate startingDate;

    @Getter
    @Setter
    private LocalDate dateClosed;

    @Getter
    @Setter
    private String status;

    @Getter @Setter
    private Long participantId;

    @Getter @Setter
    private ICIncidentReport incidentReport;

    @Getter @Setter
    private ICPatientCareInfo patientCareInfo;

    @Getter @Setter
    private ICPresentConcerns presentConcerns;

    @Getter @Setter
    private ICReferralInfo referralInfo;

    @Getter @Setter
    private ICContactNotes icContactNotes;
}
