package org.cyfwms.culturalprogram.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AttachmentDTO {
    private long culturalProgAttachmentId;
    private byte[] file;
    private String name;
    private long culturalProgramId;
    private String type;
    private String attachmentType;
    private String culturalAttachmentName;
}
