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
public class StaffInventoryDto {
    @Getter
    @Setter
    private Long staffInventoryId;

    @Getter
    @Setter
    private String item;

    @Getter
    @Setter
    private Long serialNo;

    @Getter
    @Setter
    private LocalDate from;

    @Getter
    @Setter
    private LocalDate to;

    @Getter
    @Setter
    private Boolean inUse;

    @Getter
    @Setter
    @Lob
    private String notes;

    @Getter
    @Setter
    private Long staffId;
}
