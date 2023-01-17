package org.cyfwms.common.repository;

import org.cyfwms.common.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {
    List<Reminder> findByReminderDate(LocalDate date);
}
