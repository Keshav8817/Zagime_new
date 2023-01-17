package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHCasePlanDto {
    @Getter
    @Setter
    private Long fhCasePlanId = 0L;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private String regarding;

    @Getter
    @Setter
    private String emotional;

    @Getter
    @Setter
    private String spiritual;

    @Getter
    @Setter
    private String mental;

    @Getter
    @Setter
    private String physical;

    @Getter
    @Setter
    private Long fhFileDetailsId = 0L;
}
