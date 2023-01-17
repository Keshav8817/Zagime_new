package org.cyfwms.initialcontact.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ICAttachmentDTO {
    private long icAttachmentId;
    private byte[] file;
    private String name;
    private long fileDetailsId;
    private String type;
    private String icAttachmentType;
    private String icAttachmentName;
}
