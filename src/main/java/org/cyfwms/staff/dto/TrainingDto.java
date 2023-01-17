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
public class TrainingDto {
    private Long trainingId;
    private String trainingName;
    private String status;
    private LocalDate dateOfTraining;
    private LocalDate expiryDate;
    private String notes;
    private Long staffId;
}
