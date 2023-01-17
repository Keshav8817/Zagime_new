package org.cyfwms.initialcontact.repository;

import org.cyfwms.initialcontact.entity.ICAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICAppointmentRepository extends JpaRepository<ICAppointment, Long> {
    @Query(value = "select * from ic_appointment where fileDetailsId=? AND status='active'",nativeQuery = true)
    List<ICAppointment> findByfileDetailsIdId(Long fileDetailsId);
    @Query(value = "select * from ic_appointment where appointmentid=? AND status='active'",nativeQuery = true)
    ICAppointment findByAppointmentId(long AppointmentId);
}
