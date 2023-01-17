package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHHistoryDto {
    @Getter
    @Setter
    private Long fhHistoryId = 0L;

    @Getter
    @Setter
    private String emotional;

    @Getter
    @Setter
    private String family;

    @Getter
    @Setter
    private Long fhFileDetailsId = 0L;
}
