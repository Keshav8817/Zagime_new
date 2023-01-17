package org.cyfwms.staff.repository;

import org.cyfwms.staff.entity.StaffAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffAttachmentRepo extends JpaRepository<StaffAttachment,Long> {
    @Query(value = "select * from staff_attachments s  where s.status ='ACTIVE' AND s.staffId=? ", nativeQuery = true)
    List<StaffAttachment> findByStaffId(Long staffId);
}
