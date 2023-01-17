package org.cyfwms.initialcontact.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.dto.AppointmentDto;
import org.cyfwms.common.entity.Appointments;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.common.repository.AppointmentRepository;
import org.cyfwms.initialcontact.dto.ICAppointmentDto;
import org.cyfwms.initialcontact.entity.ICAppointment;
import org.cyfwms.initialcontact.repository.ICAppointmentRepository;
import org.cyfwms.participant.dto.ParticipantAppointmentDto;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.entity.ParticipantAppointment;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ICAppointmentServiceImpl implements ICAppointmentService {
    @Autowired
    private AppointmentRepository   appointmentRepository;
    @Autowired
     ICAppointmentRepository icAppointmentRepository;
    @Autowired
    private MessageUtil  messageUtil;
    @Autowired
    private ParticipantRepository participantRepository;


    @Override
    public List<ICAppointmentDto> saveICAppointment(ICAppointmentDto icAppointmentDto) {
        log.info("Inside SaveICAppointment InitialContactAppointment");
        Appointments appointments = null;
        ICAppointment icAppointment = new ICAppointment();
        List<ICAppointmentDto>listicappointment = new ArrayList<>();
        AppointmentDto appointmentDto = new AppointmentDto();
        ICAppointmentDto icAppointmentDto1 = new ICAppointmentDto();
        if (icAppointmentDto.getIcappointmentId() == 0) {
            if(icAppointmentDto.getAppointmentDto().getRecurringAppointment().equalsIgnoreCase("Yes")){
                listicappointment = checkFrequency(icAppointmentDto);
                return listicappointment;
            }
            appointments = new Appointments();
            BeanUtils.copyProperties(icAppointmentDto.getAppointmentDto(), appointments);
            BeanUtils.copyProperties(icAppointmentDto, icAppointment);
            icAppointment.setStatus("ACTIVE");
            appointments.setAppointmentStatus("ACTIVE");
        }   // update particular user's appointment information
        else {
            icAppointment = icAppointmentRepository.findById(icAppointmentDto.getIcappointmentId()).get();
            appointments = appointmentRepository.findById(icAppointmentDto.getAppointmentDto().getAppointmentId()).get();
            BeanUtils.copyProperties(icAppointmentDto.getAppointmentDto(), appointments);
            BeanUtils.copyProperties(icAppointmentDto, icAppointment);

        }
        //save data if recurring appointment contain no value
        icAppointment.setAppointments(appointments);
        icAppointment = icAppointmentRepository.save(icAppointment);

        //copy save data into list
        BeanUtils.copyProperties(icAppointment,icAppointmentDto1);
        BeanUtils.copyProperties(appointments,appointmentDto);
        icAppointmentDto1.setAppointmentDto(appointmentDto);
        listicappointment.add(icAppointmentDto1);
        log.info("Exit SaveICAppointment InitialContactAppointment");
        return listicappointment;
    }


    public List<ICAppointmentDto> checkFrequency(ICAppointmentDto icAppointmentDto){
        Period pd = Period.between(icAppointmentDto.getAppointmentDto().getDate(), icAppointmentDto.getAppointmentDto().getEndDate());
        int difference = pd.getDays();
        int monthDiff=  pd.getMonths();
        int n = 0,counter=0,remainder=0;
        if(icAppointmentDto.getAppointmentDto().getFrequency().equalsIgnoreCase("Daily")){
            n = difference+1;
            remainder = n+1;
            counter=1;
        } else if (icAppointmentDto.getAppointmentDto().getFrequency().equalsIgnoreCase("Weekly")) {
            n = (difference)/7;
            remainder = n%7;
            n=n+1;
            counter=7;
        } else if (icAppointmentDto.getAppointmentDto().getFrequency().equalsIgnoreCase("Monthly")) {
            n=monthDiff+1;
            counter=30;
        }
        else {
            n = difference/14;
            remainder = n%14;
            n=n+1;
            counter=14;
        }
        List<ICAppointmentDto>listicAppointments = new ArrayList<>();
        listicAppointments = saveFrequency(n,counter,remainder,icAppointmentDto);
        return listicAppointments;
    }
    public List<ICAppointmentDto> saveFrequency(int n,int counter,int remainder, ICAppointmentDto icAppointmentDto) {

        List<ICAppointmentDto>listICAppointment = new ArrayList<>();
        int cnt=0;
        for(int i=0;i<n;i++){
            //save data into table
            ICAppointment icAppointment = new ICAppointment();
            Appointments appointments = new Appointments();
            BeanUtils.copyProperties(icAppointmentDto,icAppointment);
            BeanUtils.copyProperties(icAppointmentDto.getAppointmentDto(),appointments);
            icAppointment.setStatus("ACTIVE");
            appointments.setAppointmentStatus("ACTIVE");
            LocalDate l = icAppointmentDto.getAppointmentDto().getDate().plusDays(cnt);
            appointments.setDate(l);
            icAppointment.setAppointments(appointments);
            icAppointment = icAppointmentRepository.save(icAppointment);
            //copy save data into list participantAppointment
            AppointmentDto appointmentDto = new AppointmentDto();

            ICAppointmentDto ICAppointmentdto = new ICAppointmentDto();
            BeanUtils.copyProperties(icAppointment,ICAppointmentdto);
            BeanUtils.copyProperties(appointments,appointmentDto);
            ICAppointmentdto.setAppointmentDto(appointmentDto);
            listICAppointment.add(ICAppointmentdto);
            if(i==n-1 && remainder>0){
                cnt =cnt+1;

            }
            cnt=cnt+counter;
        }

        return listICAppointment;
    }
    @Override
    public List<ICAppointmentDto> readAllICAppointment(Long fileDetailsId)  {
        log.info("Inside ReadAllICAppointment InitialContactAppointment");
        List<ICAppointmentDto> participantAppointmentDto = new ArrayList<>();
        participantAppointmentDto = icAppointmentRepository.findByfileDetailsIdId(fileDetailsId).stream().map(a -> {
            AppointmentDto appointmentDto = new AppointmentDto();
            ICAppointmentDto icAppointmentDto = new ICAppointmentDto();
            BeanUtils.copyProperties(a.getAppointments(), appointmentDto);
            BeanUtils.copyProperties(a, icAppointmentDto);
            icAppointmentDto.setAppointmentDto(appointmentDto);
            return icAppointmentDto;
        }).collect(Collectors.toList());
        log.info("Exit ReadAllICAppointment InitialContactAppointment");
        return participantAppointmentDto;
    }

    @Override
    public void removeICAppointment(Long ICAppointmentId) {
        log.info("Inside RemoveICAppointment InitialContactAppointment");
        ICAppointment icAppointment = icAppointmentRepository.findById(ICAppointmentId).orElseThrow(()->new NoSuchElementFoundException(
                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                        String.valueOf(ICAppointmentId))));
        if (icAppointment.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(icAppointment)));
        }
            icAppointment.setStatus("INACTIVE");
            icAppointment.getAppointments().setAppointmentStatus("INACTIVE");
            icAppointmentRepository.save(icAppointment);
            log.info("Exit RemoveIcAppointment InitialContactAppointment");


    }

    @Override
    public ICAppointmentDto readOneAppointment(Long ICAppontmentId) {
        log.info("Inside ReadOneAppointment InitialContactAppointment");
        ICAppointmentDto icAppointmentDto = new ICAppointmentDto();
        AppointmentDto appointmentDto = new AppointmentDto();
        if (ICAppontmentId != 0) {
            Optional<ICAppointment> ICAppointment = icAppointmentRepository.findById(ICAppontmentId);

            if (ICAppointment.isPresent()) {
                if (ICAppointment.get().getStatus().equals("ACTIVE")){
                    BeanUtils.copyProperties(ICAppointment.get(), icAppointmentDto);
                    BeanUtils.copyProperties(ICAppointment.get().getAppointments(),appointmentDto);
                    Long p= Long.parseLong(appointmentDto.getClient());
                    if (p!=0) {
                        Participant participant=participantRepository.findByParticipantId(p);
                        appointmentDto.setClient(participant.getFirstname()+" "+participant.getSurname());
                        appointmentDto.setParticipantId(participant.getParticipantId());
                    }
                    icAppointmentDto.setAppointmentDto(appointmentDto);
                    if (icAppointmentDto.getAppointmentDto().getDate() == null) {
                        icAppointmentDto.getAppointmentDto().setDate(LocalDate.of(1, 1, 1));
                    }
                    if (icAppointmentDto.getAppointmentDto().getTime() == null) {
                        icAppointmentDto.getAppointmentDto().setTime(LocalTime.of(1, 1, 1));
                    }

                }
            }
        }
        log.info("Exit ReadOneAppointment InitialContactAppointment");
        return icAppointmentDto;

    }

}
