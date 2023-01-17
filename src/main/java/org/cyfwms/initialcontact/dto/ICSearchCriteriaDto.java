package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICSearchCriteriaDto {
    @Getter
    @Setter
    private String clientName;

    @Getter
    @Setter
    private Long fileNumber;

    @Getter
    @Setter
    private String caseworker;

    @Getter
    @Setter
    private LocalDate startingDate;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String typeOfPatient;
}
