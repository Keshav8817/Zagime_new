package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantIdentityDto {
    @Getter @Setter
    private Long participantId=0L;
    @Getter @Setter
    private String firstname;
    @Getter @Setter
    private String surname;
    @Getter @Setter
    private String middleName;
    @Getter @Setter
    private LocalDate dateOfBirth;
    @Getter @Setter
    private String gender;
    @Getter @Setter
    private String maritalStatus;
    @Getter @Setter
    private Long referenceId;
    @Getter @Setter
    private Long participantImageId;
    @Getter @Setter
    private String participantImageType;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String participantImageName;
    @Getter @Setter
    private Boolean removeProfilePicture=false;
    @Getter @Setter
    private byte[] image;

}
