package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.cyfwms.common.dto.AppointmentDto;





@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor

public class ParticipantAppointmentDto {
    @Getter @Setter
    private Long participantAppointmentId=0l;
    @Getter @Setter
    private Long participantId;
    @Getter @Setter
    private Long referenceId;
    @Getter @Setter
    private AppointmentDto appointmentdto;


}
