package org.cyfwms.participant.service;

import org.cyfwms.participant.dto.ParticipantAttachmentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ParticipantAttachmentService {
    ParticipantAttachmentDto saveParticipantAttachment(
            ParticipantAttachmentDto participantAttachmentDto);
    ParticipantAttachmentDto readParticipantAttachmentByTypeAndStatus(
            Long participantId, String attachmentType, String status);

    ParticipantAttachmentDto uploadParticipantAttachment(MultipartFile file, String participantDto) throws IOException;

    void removeParticipantAttachment(Long participantAttachmentId);

    List<ParticipantAttachmentDto> getAllFiles(Long participantId);

    ParticipantAttachmentDto getOneFile(Long participantAttachmentId);

    ParticipantAttachmentDto downloadOne(Long participantAttachmentId);

}

