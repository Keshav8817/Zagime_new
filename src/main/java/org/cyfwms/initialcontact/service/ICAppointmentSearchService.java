package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICAppointmentSearchDto;
import org.cyfwms.initialcontact.dto.ICAppointmentSearchResultDto;
import org.cyfwms.initialcontact.repository.ICAppointmentSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ICAppointmentSearchService {
    @Autowired
    ICAppointmentSearchRepo icAppointmentSearchRepo;

    public List<ICAppointmentSearchResultDto> searchICAppointment(ICAppointmentSearchDto icAppointmentSearchDto) {
        return icAppointmentSearchRepo.searchICAppointment(icAppointmentSearchDto);
    }
}