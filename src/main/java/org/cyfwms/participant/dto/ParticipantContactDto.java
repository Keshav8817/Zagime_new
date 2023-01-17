package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantContactDto {
    @Getter
    @Setter
    private Long participantId;

    @Getter
    @Setter
    private Long participantContactId=0L;

    @Getter
    @Setter
    private String addressLine1;

    @Getter
    @Setter
    private String addressLine2;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String province;

    @Getter
    @Setter
    private String postalCode;

    @Getter
    @Setter
    private String homePhone;

    @Getter
    @Setter
    private String workPhone;

    @Getter
    @Setter
    private String cellPhone;

    @Getter
    @Setter
    private String emailAddress;
}
