package org.cyfwms.initialcontact.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICContactNotesSearchCriteriaDto {
    @Getter @Setter
    private Long fileDetailsId;

    @Getter @Setter
    private String data;
}
