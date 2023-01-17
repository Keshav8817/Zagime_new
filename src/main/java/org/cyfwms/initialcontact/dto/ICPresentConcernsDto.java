package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICPresentConcernsDto {
    @Getter
    @Setter
    private Long fileDetailsId;

    @Getter
    @Setter
    private Long presentConcernsId=0L;

    @Getter
    @Setter
    private String selectPresentConcerns;

    @Getter
    @Setter
    private String situation;

    @Getter
    @Setter
    private String substanceAbuse;

    @Getter
    @Setter
    private String explainMentalHealth;
}
