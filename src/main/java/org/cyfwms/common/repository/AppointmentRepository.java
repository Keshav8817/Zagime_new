package org.cyfwms.common.repository;

import org.cyfwms.common.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments,Long> {
    @Query(value = "select * from appointments where appointmentid=? AND appointmentStatus ='Active'",nativeQuery = true)
    Appointments findByAppointmentId(Long appointmentIdId);
   @Query(value = "select * from appointments where date=? AND appointmentStatus ='ACTIVE' ",nativeQuery = true)
    List<Appointments> findByDate(LocalDate date);
}
