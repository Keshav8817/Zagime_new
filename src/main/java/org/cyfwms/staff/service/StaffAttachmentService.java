package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.StaffAttachmentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StaffAttachmentService {

    StaffAttachmentDto saveStaffAttachment(MultipartFile file, String staffDto) throws IOException;

    StaffAttachmentDto getOneFile(Long staffAttachmentId);

    List<StaffAttachmentDto> getAllFiles(Long staffId);

    void removeStaffFile(Long staffAttachmentId);
}
