package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.cyfwms.common.dto.AppointmentDto;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICAppointmentDto {
    @Getter @Setter
    private Long  icappointmentId=0l;
    @Getter @Setter
    private Long  fileDetailsId;
    @Getter @Setter
    private Long fileDetailsNo;
    @Getter @Setter
    private AppointmentDto appointmentDto;


}
