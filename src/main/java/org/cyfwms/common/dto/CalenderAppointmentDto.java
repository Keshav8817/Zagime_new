package org.cyfwms.common.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CalenderAppointmentDto {
    private Long appointmentId;
    private String subject;
    private String status;
    private LocalDate date;
    @Getter
    @Setter
    private Long participantId;
    @Getter
    @Setter
    private Long fileDetailsId;

    @Getter
    @Setter
    private Long cgProviderId;

}
