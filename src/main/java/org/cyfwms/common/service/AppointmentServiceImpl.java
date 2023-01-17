package org.cyfwms.common.service;
import org.cyfwms.common.dto.AppointmentDto;
import org.cyfwms.common.dto.CalenderAppointmentDto;
import org.cyfwms.common.dto.CalenderDto;
import org.cyfwms.common.dto.CalenderReminderDto;
import org.cyfwms.common.entity.Appointments;
import org.cyfwms.common.entity.Reminder;
import org.cyfwms.common.repository.AppointmentRepository;
import org.cyfwms.common.repository.ReminderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReminderRepository reminderRepository;


    @Override
    public List<CalenderAppointmentDto> getAllCommonCalenderDate(LocalDate date) {
        List<Appointments> appointments = appointmentRepository.findByDate(date);

        List<CalenderAppointmentDto> calenderAppointmentDtoList = appointments.stream().
                map(ca -> {
                    CalenderAppointmentDto calenderAppointmentDto = new CalenderAppointmentDto();
                    BeanUtils.copyProperties(ca, calenderAppointmentDto);
                    if (ca.getParticipantAppointment() != null) {
                        calenderAppointmentDto.setParticipantId(ca.getParticipantAppointment().getParticipantId());
                    }
                    if (ca.getIcAppointment() != null) {
                        calenderAppointmentDto.setFileDetailsId(ca.getIcAppointment().getFileDetailsId());
                    }

                    return calenderAppointmentDto;
                }).collect(Collectors.toList());

        return calenderAppointmentDtoList;
    }

    @Override
    public List<Object> getAllCalenderData() {
        List<Object> combined = new ArrayList<>();
        List<Appointments> appointments = appointmentRepository.findAll();
        List<CalenderAppointmentDto> calenderAppointmentDtoList = appointments.stream().filter(a -> a.getAppointmentStatus().equalsIgnoreCase("ACTIVE")).
                map(ca -> {
                    CalenderAppointmentDto calenderAppointmentDto = new CalenderAppointmentDto();
                    BeanUtils.copyProperties(ca, calenderAppointmentDto);
                    if (ca.getParticipantAppointment() != null) {
                        calenderAppointmentDto.setParticipantId(ca.getParticipantAppointment().getParticipantId());
                    }
                    if (ca.getIcAppointment() != null) {
                        calenderAppointmentDto.setFileDetailsId(ca.getIcAppointment().getFileDetailsId());
                    }

                    return calenderAppointmentDto;
                }).collect(Collectors.toList());

        List<Reminder> reminders = reminderRepository.findAll();
        List<CalenderReminderDto> calenderAppointmentDtoLis = reminders.stream().filter(a->a.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).
                map(ca -> {
                    CalenderReminderDto calenderAppointmentDto = new CalenderReminderDto();
                    BeanUtils.copyProperties(ca, calenderAppointmentDto);
                    if (ca.getParticipantReminder() != null) {
                        calenderAppointmentDto.setParticipantId(ca.getParticipantReminder().getParticipantId());
                    }
                    if (ca.getICReminder() != null) {
                        calenderAppointmentDto.setFileDetailsId(ca.getICReminder().getFileDetailsId());
                    }

                    return calenderAppointmentDto;
                }).collect(Collectors.toList());
        combined = Stream.concat(calenderAppointmentDtoList.stream(), calenderAppointmentDtoLis.stream()).collect(Collectors.toList());
        return combined;
    }
}