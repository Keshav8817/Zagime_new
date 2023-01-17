package org.cyfwms.common.service;

import org.cyfwms.common.dto.AppointmentDto;
import org.cyfwms.common.dto.CalenderAppointmentDto;
import org.cyfwms.common.dto.CalenderDto;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    List<CalenderAppointmentDto> getAllCommonCalenderDate(LocalDate dateTime);

    List<Object> getAllCalenderData();
}
