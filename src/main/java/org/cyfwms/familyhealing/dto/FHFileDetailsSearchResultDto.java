package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class FHFileDetailsSearchResultDto {
    @Getter
    @Setter
    private Long fhFileDetailsId;

    @Getter
    @Setter
    private Long fileNo;

    @Getter
    @Setter
    private String clientName;

    @Getter
    @Setter
    private LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String community;

    @Getter
    @Setter
    private String department;

    @Getter
    @Setter
    private String caseworker;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private String reason;

    @Getter
    @Setter
    private String notes;

    public FHFileDetailsSearchResultDto(Long fhFileDetailsId, Long fileNo, String clientName, LocalDate startDate, LocalDate endDate, String status, String community, String department, String caseworker, LocalDate date, String reason, String notes) {
        this.fhFileDetailsId = fhFileDetailsId;
        this.fileNo = fileNo;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.community = community;
        this.department = department;
        this.caseworker = caseworker;
        this.date = date;
        this.reason = reason;
        this.notes = notes;
    }
}
