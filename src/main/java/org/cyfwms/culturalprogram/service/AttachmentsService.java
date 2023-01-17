package org.cyfwms.culturalprogram.service;

import org.cyfwms.culturalprogram.dto.AttachmentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentsService {
    AttachmentDTO uploadAttachment(MultipartFile file, String culturalDto) throws IOException;

    AttachmentDTO getOneFile(Long id);

    void removeCulturalProgAttachment(Long culturalProgImageId);

    List<AttachmentDTO> getAllFiles(Long culturalProgramId);
}
