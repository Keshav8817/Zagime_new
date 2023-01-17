package org.cyfwms.common.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.dto.CalenderAppointmentDto;
import org.cyfwms.common.dto.CalenderReminderDto;
import org.cyfwms.common.service.AppointmentService;
import org.cyfwms.common.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j(topic = "Common_Controller")
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/v1/commonservice")
@CrossOrigin("*")
public class CommonApi {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ReminderService reminderService;

    @GetMapping(value = "/appointment/getAllCalenderDate/{date}", produces = "application/json")
    @ApiOperation("Calender Api Of Appointment")
    @ResponseStatus(HttpStatus.OK)
    public List<CalenderAppointmentDto> getAllCommonCalenderDate(@PathVariable("date") String date) {
        LocalDate dateTime=null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateTime = LocalDate.parse(date, formatter);
        return appointmentService.getAllCommonCalenderDate(dateTime);
    }


    @GetMapping(value = "/reminder/getAllCalenderDate/{date}", produces = "application/json")
    @ApiOperation("Calender Api Of Reminder")
    @ResponseStatus(HttpStatus.OK)
    public List<CalenderReminderDto> getAllReminderCalenderDate(@PathVariable("date") String date) {
        LocalDate dateTime=null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateTime = LocalDate.parse(date, formatter);
        return reminderService.getAllReminderCalenderDate(dateTime);
    }


    @GetMapping(value = "/appointment/getAllCalenderData", produces = "application/json")
    @ApiOperation("Calender Api Of Appointment")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getAllCalenderData() {

        return appointmentService.getAllCalenderData();
    }


}
