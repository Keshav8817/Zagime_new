package org.cyfwms.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.cyfwms.common.entity.Attachment;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantAttachmentDto {
    private Long participantAttachmentId;
    private Long participantId;
    private String name;
    private String type;
    private Attachment attachment;
    @Getter
    @Setter
    private byte[] image;
    @Getter
    @Setter
    private String participantImageName;
    private String status;
    private LocalDateTime lastwritten;
    private String attachmentType;


}
