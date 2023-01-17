package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CriminalHistoryRecordDto{
    @Getter @Setter
    private Long criminalHistoryRecordId;

    @Getter @Setter
    private LocalDate arrestDate;

    @Getter @Setter
    private String charges;

    @Getter @Setter
    private String conviction;

    @Getter @Setter
    private String sentence;

    @Getter @Setter
    private Long criminalHistoryId;
}
