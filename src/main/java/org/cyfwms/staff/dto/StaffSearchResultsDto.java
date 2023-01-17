package org.cyfwms.staff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class StaffSearchResultsDto {
    @Getter
    @Setter
    private Long staffId;

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
    private LocalDate dateOfBirth;

    @Getter
    @Setter
    private String department;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String workLocation;

    @Getter
    @Setter
    private String supervisor;

    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private String employeeId;

    public StaffSearchResultsDto(Long staffId, String firstName, String middleName, String lastName, LocalDate dateOfBirth, String department, String status, String workLocation, String supervisor, boolean active, String employeeId) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.status = status;
        this.workLocation = workLocation;
        this.supervisor = supervisor;
        this.active = active;
        this.employeeId = employeeId;
    }
}
