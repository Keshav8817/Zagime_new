package org.cyfwms.staff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffAttachmentDto {
    private long staffAttachmentId;
    private byte[] staffAttachmentFile;
    private String name;
    private long staffId;
    private String type;
    private String staffAttachmentType;
    private  String staffAttachmentName;

}
