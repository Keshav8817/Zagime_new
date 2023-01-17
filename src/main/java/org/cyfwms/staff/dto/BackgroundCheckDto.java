package org.cyfwms.staff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BackgroundCheckDto {
    private Long backgrounCheckId;
    private String typeOfCheck;
    private String status;
    private LocalDate dateRequested;
    private LocalDate dateCompleted;
    private String requestedBy;
    private Long staffId;
    private  String notes;
    private Long RequestedById;

}
