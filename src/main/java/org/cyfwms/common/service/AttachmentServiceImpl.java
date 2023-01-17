package org.cyfwms.common.service;

import org.cyfwms.common.entity.Attachment;
import org.cyfwms.common.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment saveAttachment(Attachment attachment){
        return attachmentRepository.save(attachment);
    }

}
