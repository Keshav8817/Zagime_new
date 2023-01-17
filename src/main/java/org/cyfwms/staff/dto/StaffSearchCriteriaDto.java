package org.cyfwms.staff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class StaffSearchCriteriaDto {
    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String middleName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String workLocation;

    @Getter
    @Setter
    private String supervisor;

    @Getter
    @Setter
    private boolean active;

}
