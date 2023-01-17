package org.cyfwms.common.service;

import org.cyfwms.common.dto.CalenderReminderDto;

import java.time.LocalDate;
import java.util.List;

public interface ReminderService {
    List<CalenderReminderDto> getAllReminderCalenderDate(LocalDate dateTime);
}
