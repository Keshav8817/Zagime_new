package org.cyfwms.familyhealing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHReferralDto {
    @Getter
    @Setter
    private Long fhReferralId = 0L;

    @Getter
    @Setter
    private LocalDate referralDate;

    @Getter
    @Setter
    private String referredBy;

    @Getter
    @Setter
    private String reason;

    @Getter
    @Setter
    private String pleaseDescribe;

    @Getter
    @Setter
    private String notes;

    @Getter
    @Setter
    private Long fhFileDetailsId;
}
