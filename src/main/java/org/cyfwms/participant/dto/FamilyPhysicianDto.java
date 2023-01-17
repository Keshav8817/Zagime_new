package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FamilyPhysicianDto {
    @Getter
    @Setter
    private Long participantId;

    @Getter
    @Setter
    private Long familyPhysicianId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String cell;

    @Getter
    @Setter
    private String listOfMedication;
}
