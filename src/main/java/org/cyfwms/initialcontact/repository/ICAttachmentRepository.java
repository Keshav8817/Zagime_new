package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICAttachmentRepository extends JpaRepository<ICAttachmentEntity, Long> {
    @Query(value = "select * from ic_attachments i left join ic_filedetails c on i.filedetailsid = c.filedetailsid where i.status ='ACTIVE' AND i.filedetailsid=? ", nativeQuery = true)
    List<ICAttachmentEntity> findByFileDetailsId(Long fileDetailsId);
}
