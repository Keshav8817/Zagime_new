package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ICSearchResultsDto {
    @Getter
    @Setter
    private Long fileDetailsId;

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


    public ICSearchResultsDto(Long fileDetailsId, String clientName, Long fileNumber, String caseworker, LocalDate startingDate, String status) {
        this.fileDetailsId = fileDetailsId;
        this.clientName = clientName;
        this.fileNumber = fileNumber;
        this.caseworker = caseworker;
        this.startingDate = startingDate;
        this.status = status;
    }
}
