package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantSearchCriteriaDto {
    @Getter @Setter
    private String firstname;
    @Getter @Setter
    private String surname;
    @Getter @Setter
    private String middleName;
    @Getter @Setter
    private LocalDate dateOfBirth;
    @Getter @Setter
    private String maritalStatus;
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String phoneNumber;
   @Getter @Setter
    private Long referenceId;


}
