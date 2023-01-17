package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICAppointmentDto;

import java.util.List;

public interface ICAppointmentService {

    List<ICAppointmentDto> saveICAppointment(ICAppointmentDto icAppointmentDto);
    List<ICAppointmentDto> readAllICAppointment(Long fileDetailsId) throws Exception;
    void removeICAppointment(Long ICAppointmentId);
    ICAppointmentDto readOneAppointment(Long ICAppointmentId);
}
