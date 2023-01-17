package org.cyfwms.familyhealing.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHFileDetailsDto {
    @Getter
    @Setter
    private Long fhFileDetailsId=0L;

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

    @Getter
    @Setter
    private Long participantId;
}
