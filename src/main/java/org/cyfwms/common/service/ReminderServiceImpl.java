package org.cyfwms.common.service;

import org.cyfwms.common.dto.CalenderReminderDto;
import org.cyfwms.common.entity.Reminder;
import org.cyfwms.common.repository.ReminderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ReminderServiceImpl implements ReminderService{
    @Autowired
    private ReminderRepository reminderRepository;
    @Override
    public List<CalenderReminderDto> getAllReminderCalenderDate(LocalDate date) {
        List<Reminder> reminders = reminderRepository.findByReminderDate(date);

        List<CalenderReminderDto> calenderReminderDtoDtoList = reminders.stream().
                map(cr -> {
                    CalenderReminderDto calenderReminderDto = new CalenderReminderDto();
                    BeanUtils.copyProperties(cr, calenderReminderDto);
                    if (cr.getParticipantReminder() != null) {
                        calenderReminderDto.setParticipantId(cr.getParticipantReminder().getParticipantId());
                    }
                    if (cr.getICReminder() != null) {
                        calenderReminderDto.setFileDetailsId(cr.getICReminder().getFileDetailsId());
                    }

                    return calenderReminderDto;
                }).collect(Collectors.toList());

        return calenderReminderDtoDtoList;
    }
}
