package org.cyfwms.culturalprogram.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CPASearchCriteriaDto {
    @Getter
    @Setter
    private Long culturalProgramId;
    @Getter
    @Setter
    private String data;
}
