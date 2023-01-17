package org.cyfwms.culturalprogram.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CPAIdentityDto {
    @Getter @Setter
    private Long culturalProgramId=0L;
    @Getter @Setter
    private Long referenceId;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private String caseworker;
    @Getter @Setter
    private LocalDate startDate;
    @Getter @Setter
    private LocalDate endDate;
    @Getter @Setter
    private String totalCost;
    @Getter @Setter
    private String totalParticipation;
    @Getter @Setter
    private String sessionDetails;
    @Getter @Setter
    private String costOrParticipationDetails;
    @Getter @Setter
    private String outcomes;
    @Getter @Setter
    private String notes;
}
