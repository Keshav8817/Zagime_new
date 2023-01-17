package org.cyfwms.participant.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.cyfwms.participant.entity.*;
import lombok.*;
import org.cyfwms.participant.entity.*;

import java.time.LocalDate;
import java.util.List;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantCommonDataDto {

    @Getter @Setter
    private Long participantId;
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
    private ParticipantContactDto participantContact;
    @Getter @Setter
    private List<HouseholdMemberDto> householdMember;
    @Getter @Setter
    private EducationDto education;
    @Getter @Setter
    private EmploymentDto employment;
    @Getter @Setter
    private CriminalHistoryDto criminalHistory;
    @Getter @Setter
    private List<FamilyPhysicianDto> familyPhysicians;
    @Getter @Setter
    private List<CounselorCFSWorkersDto> counselorCFSWorker;
    @Getter @Setter
    private ParticipantOtherInformationServiceDto participantOtherInfo;
}
