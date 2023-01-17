package org.cyfwms.initialcontact.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ICContactNotesSearchResultsDto {
    @Getter
    @Setter
    private Long contactNotesId;

    @Getter
    @Setter
    private Long fileDetailsId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String worker;

    @Getter
    @Setter
    private LocalDate date;

    @Getter
    @Setter
    private LocalTime time;


    @Getter
    @Setter
    private String contactMethod;

    @Getter
    @Setter
    private String needAddress;

    @Getter
    @Setter
    private String summary;

    @Getter
    @Setter
    private String result;

    @Getter
    @Setter
    private String nextStep;

    @Getter
    @Setter
    private String casePlanProgress;

    @Getter
    @Setter
    private String additionalInformation;

    public ICContactNotesSearchResultsDto(Long contactNotesId, Long fileDetailsId, String name, String worker, LocalDate date, LocalTime time, String contactMethod, String needAddress, String summary, String result, String nextStep, String casePlanProgress, String additionalInformation ) {
        this.contactNotesId=contactNotesId;
        this.fileDetailsId=fileDetailsId;
        this.name=name;
        this.worker=worker;
        this.date=date;
        this.time=time;
        this.contactMethod = contactMethod;
        this.needAddress=needAddress;
        this.summary=summary;
        this.result=result;
        this.nextStep=nextStep;
        this.casePlanProgress=casePlanProgress;
        this.additionalInformation=additionalInformation;
    }
}
