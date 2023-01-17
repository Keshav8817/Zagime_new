package org.cyfwms.staff.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.Lob;
import java.time.LocalDate;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class StaffJobAndBankingDto {

    @Getter
    @Setter
    private Long staffJobAndBankingId=0l;

    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @Getter
    @Setter
    private String workPhone;

    @Getter
    @Setter
    private String workEmail;

    @Getter
    @Setter
    private String jobTitle;

    @Getter
    @Setter
    private Long salary;

    @Getter
    @Setter
    private String bankAccount;

    @Getter
    @Setter
    private String transit;

    @Getter
    @Setter
    private String bank;

    @Getter
    @Setter
    private String bankName;

    @Getter
    @Setter
    @Lob
    private String bankAddress;

    @Getter
    @Setter
    private Long staffId;
}
