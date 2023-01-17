package org.cyfwms.initialcontact.repository;
import org.cyfwms.initialcontact.entity.ICContactNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ICContactNotesRepository extends JpaRepository<ICContactNotes,Long> {
    @Query(value = "select * from iccontactnotes i where i.status='ACTIVE' AND fileDetailsId=?",nativeQuery = true)
    ICContactNotes findByFileDetailsId(Long fileDetailsId);
}
