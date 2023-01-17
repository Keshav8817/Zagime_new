package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICSearchReminderDto;
import org.cyfwms.initialcontact.dto.ICSearchReminderResultDto;
import org.cyfwms.initialcontact.repository.ICSearchReminderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICSearchReminderService {
    @Autowired
    private ICSearchReminderRepo iCSearchReminderRepo;
    public List<ICSearchReminderResultDto> searchICReminder(ICSearchReminderDto iCSearchReminderDto) {
        return iCSearchReminderRepo.searchICReminder(iCSearchReminderDto);
    }
}
