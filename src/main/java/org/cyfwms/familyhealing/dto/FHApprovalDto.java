package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHApprovalDto {
    @Getter
    @Setter
    private Long fhApprovalId = 0L;

    @Getter
    @Setter
    private String submittedBy;

    @Getter
    @Setter
    private LocalDate submittedDate;

    @Getter
    @Setter
    private String approvedBy;

    @Getter
    @Setter
    private LocalDate approvedDate;

    @Getter
    @Setter
    private String comments;

    @Getter
    @Setter
    private Long fhFileDetailsId = 0L;
}
