package org.cyfwms.familyhealing.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHSafetyPlanDto {
    @Getter
    @Setter
    private Long fhSafetyPlanId;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private String participant;

    @Getter
    @Setter
    private String signs;

    @Getter
    @Setter
    private String strategies;

    @Getter
    @Setter
    private String crisis;

    @Getter
    @Setter
    private String printName;

    @Getter
    @Setter
    private Long fhFileDetailsId;

}
