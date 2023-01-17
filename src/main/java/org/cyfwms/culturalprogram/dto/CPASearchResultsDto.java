package org.cyfwms.culturalprogram.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CPASearchResultsDto {
    @Getter @Setter
    private Long CulturalProgramId;
    @Getter @Setter
    private Long referenceId;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String type;
    @Getter @Setter
    private String caseworker;
    @Getter @Setter
    private LocalDate startDate;
    @Getter @Setter
    private String status;
    public CPASearchResultsDto(Long culturalProgramId, Long referenceId, String name, String type, String caseworker, LocalDate startDate, String status) {
        CulturalProgramId = culturalProgramId;
        this.referenceId = referenceId;
        this.name = name;
        this.type = type;
        this.caseworker = caseworker;
        this.startDate = startDate;
        this.status = status;
    }
}
