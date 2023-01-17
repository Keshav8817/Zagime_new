package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ICAppointmentSearchDto {
    @Getter
    @Setter
    private Long fileDetailsId;
    @Getter @Setter
    private String data;
    @Getter@Setter
    private Long icappointmentId;
}
