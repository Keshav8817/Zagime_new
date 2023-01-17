package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantSearchResultsDto {
    @Getter @Setter
    private Long participantId;
    @Getter @Setter
    private String firstname;
    @Getter @Setter
    private String middleName;
    @Getter @Setter
    private String surname;
    @Getter @Setter
    private LocalDate dateOfBirth;
    @Getter @Setter
    private String maritalStatus;
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String homePhone;
    @Getter @Setter
    private String workPhone;
    @Getter @Setter
    private String cellPhone;
   @Getter @Setter
   private Long referenceId;



    public ParticipantSearchResultsDto(Long participantId, String firstname, String middleName, String surname, String maritalStatus, LocalDate dateOfBirth, String city, String homePhone, String workPhone, String cellPhone,Long referenceId) {
        this.participantId = participantId;
        this.firstname = firstname;
        this.middleName = middleName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
        this.city = city;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.cellPhone = cellPhone;
        this.referenceId=referenceId;
    }

}
