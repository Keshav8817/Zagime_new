package org.cyfwms.familyhealing.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class FHConsentFormsDto {
    @Getter
    @Setter
    private Long fhConsentFormsId;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String pleaseSpecify;

    @Getter
    @Setter
    private byte[] file;


    @Getter
    @Setter
    private Long fhFileDetailsId;


    private String fhAttachmentType;

    private String fhAttachmentName;
}
