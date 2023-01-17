package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CriminalHistoryDto{
    @Getter @Setter
    private Long participantId;

    @Getter @Setter
    private Long criminalHistoryId;

    @Getter @Setter
    private List<CriminalHistoryRecordDto> criminalHistoryRecordList;

    @Getter @Setter
    private boolean probation;

    @Getter @Setter
    private boolean parole;

    @Getter @Setter
    private String conditions;

    @Getter @Setter
    private String courtWorkerAndContactInfo;
}
