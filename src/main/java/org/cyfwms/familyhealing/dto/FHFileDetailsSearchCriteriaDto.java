package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHFileDetailsSearchCriteriaDto {
    @Getter
    @Setter
    private String clientName;

    @Getter
    @Setter
    private Long fileNo;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String community;

    @Getter
    @Setter
    private String caseworker;

    @Getter
    @Setter
    private LocalDate startDate;
}
