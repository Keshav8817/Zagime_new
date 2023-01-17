package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICReminderDto;

import java.util.List;

public interface ICReminderService {

    List<ICReminderDto> saveICReminder(ICReminderDto icReminderDto);

    ICReminderDto readICReminder(Long icReminderId);

    List<ICReminderDto> readAllICReminder(Long fileDetailsId);

    void removeICReminder(Long icReminderId);

}
