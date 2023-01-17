package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICAttachmentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICAttachmentService {
    ICAttachmentDTO uploadAttachment(MultipartFile file, String icDto) throws IOException;

    ICAttachmentDTO getOneFile(Long icAttachmentId);

    List<ICAttachmentDTO> getAllFiles(Long fileDetailsId);

    void removeICAttachment(Long icAttachmentId);
}
