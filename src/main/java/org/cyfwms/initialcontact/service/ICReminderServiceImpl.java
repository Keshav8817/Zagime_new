package org.cyfwms.initialcontact.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.dto.ReminderDto;
import org.cyfwms.common.entity.Reminder;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.common.repository.ReminderRepository;
import org.cyfwms.common.util.FrequencyGeneratorUtil;
import org.cyfwms.initialcontact.dto.ICReminderDto;
import org.cyfwms.initialcontact.entity.ICReminder;
import org.cyfwms.initialcontact.repository.ICReminderRepository;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ICReminderServiceImpl implements ICReminderService {

    @Autowired
    private ICReminderRepository icReminderRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private FrequencyGeneratorUtil frequencyGeneratorUtil;

    @Override
    public List<ICReminderDto> saveICReminder(ICReminderDto icReminderDto) {
        log.info("Inside InitialContact SaveICtReminder");
        Reminder reminder = null;
        ReminderDto reminderDto=new ReminderDto();
        ICReminderDto icReminderDto1=new ICReminderDto();
        List<ICReminderDto> icReminderDtoList =new ArrayList<>();
        ICReminder icReminder = new ICReminder();
        if (icReminderDto.getIcReminderId() == 0) {
            if (!icReminderDto.getReminderDto().getFrequency().isEmpty()){
            icReminderDtoList =checkFrequency(icReminderDto);
            return icReminderDtoList;
           }
            reminder = new Reminder();
            BeanUtils.copyProperties(icReminderDto.getReminderDto(), reminder);
            BeanUtils.copyProperties(icReminderDto, icReminder);
            icReminder.setStatusOfDeletion("ACTIVE");
            reminder.setStatusOfDeletion("ACTIVE");

        } else {
            icReminder = icReminderRepository.findById(icReminderDto.getIcReminderId()).get();
            reminder = reminderRepository.findById(icReminderDto.getReminderDto().getReminderId()).get();
            BeanUtils.copyProperties(icReminderDto.getReminderDto(), reminder);

            BeanUtils.copyProperties(icReminderDto, icReminder);

        }
        icReminder.setReminder(reminder);
        icReminder = icReminderRepository.save(icReminder);

        BeanUtils.copyProperties(icReminder,icReminderDto1);
        BeanUtils.copyProperties(reminder,reminderDto);
        icReminderDto1.setReminderDto(reminderDto);
        icReminderDtoList.add(icReminderDto1);
        log.info("Exit InitialContact SaveICReminder");
        return icReminderDtoList;
    }

    public List<ICReminderDto> checkFrequency(ICReminderDto icReminderDto) {
        Period pd = Period.between(icReminderDto.getReminderDto().getReminderDate() ,icReminderDto.getReminderDto().getEndDate());
        int difference = pd.getDays();
        int monthDiff = pd.getMonths();

        int n = 0,counter=0, rem =0;
        if(icReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Daily")){
            n = difference+1;
            rem = n+1;
            counter=1;
        } else if (icReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Weekly")) {
            n = difference / 7;
            rem = n % 7;
            n = n + 1;
            counter = 7;
        } else if (icReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Monthly")) {

            n = monthDiff + 1;
            counter = 31;
        }else {
            n = difference / 14;
            rem = n % 14;
            n = n + 1;
            counter = 14;
        }
        List<ICReminderDto> iCReminderDtoList =new ArrayList<>();
        iCReminderDtoList = saveFrequency(n,counter,rem,icReminderDto);
        return iCReminderDtoList;
    }

    public List<ICReminderDto> saveFrequency(int n, int counter, int rem, ICReminderDto iCReminderDto) {
        List<ICReminderDto> iCReminderDtoList = new ArrayList<>();
        int cnt=0;
        for(int i=0;i<n;i++){
            ICReminder icReminder=new ICReminder();
            Reminder reminder=new Reminder();
            BeanUtils.copyProperties(iCReminderDto,icReminder);
            BeanUtils.copyProperties(iCReminderDto.getReminderDto(),reminder);
            icReminder.setStatusOfDeletion("ACTIVE");
            reminder.setStatusOfDeletion("ACTIVE");
            LocalDate localDate=iCReminderDto.getReminderDto().getReminderDate().plusDays(cnt);
            reminder.setReminderDate(localDate);
            icReminder.setReminder(reminder);
            icReminder = icReminderRepository.save(icReminder);

            ReminderDto reminderDto = new ReminderDto();

            ICReminderDto iCReminderdto=new ICReminderDto();
            BeanUtils.copyProperties(icReminder,iCReminderdto);
            BeanUtils.copyProperties(reminder,reminderDto);
            iCReminderdto.setReminderDto(reminderDto);
            iCReminderDtoList.add(iCReminderdto);
            if(i==n-1 && rem>0){
                cnt =cnt+1;
            }
            if (iCReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Monthly")) {
                int year = iCReminderDto.getReminderDto().getReminderDate().getYear();
                int month = iCReminderdto.getReminderDto().getReminderDate().getMonth().getValue();
                int monthly = frequencyGeneratorUtil.generateMonthlyUtil(month, cnt, year, counter);
                cnt = monthly;
            } else {
                cnt = cnt + counter;
            }
        }
        return iCReminderDtoList;
    }

    @Override
    public ICReminderDto readICReminder(Long icReminderId) {
        log.info("Inside InitialContact ReadICReminder");
        ICReminderDto icReminderDto = new ICReminderDto();
        ReminderDto reminderDto = new ReminderDto();
        if (icReminderId != 0) {
            Optional<ICReminder> icReminder = icReminderRepository.findById(icReminderId);

            if (icReminder.isPresent()) {
                if (icReminder.get().getStatusOfDeletion().equals("ACTIVE")) {
                    BeanUtils.copyProperties(icReminder.get(), icReminderDto);
                    BeanUtils.copyProperties(icReminder.get().getReminder(), reminderDto);
                    Long participantId = Long.parseLong(reminderDto.getRegarding());
                    if(participantId!=0){
                        Participant participant = participantRepository.findByParticipantId(participantId);
                        reminderDto.setRegarding(participant.getFirstname() + " " + participant.getSurname());
                        reminderDto.setParticipantId(participant.getParticipantId());
                    }
                    icReminderDto.setReminderDto(reminderDto);
                }
            }
        }
        log.info("Exit InitialContact ReadICReminder");
        return icReminderDto;

    }

    @Override
    public List<ICReminderDto> readAllICReminder(Long fileDetailsId) {
        log.info("Inside InitialContact ReadAllICReminder");
        List<ICReminderDto> icReminderDtoList = new ArrayList<>();
        icReminderDtoList = icReminderRepository.findByFileDetailsId(fileDetailsId).stream().map(a -> {
            ReminderDto reminderDto = new ReminderDto();
            ICReminderDto icReminderDto = new ICReminderDto();
            BeanUtils.copyProperties(a.getReminder(), reminderDto);
            BeanUtils.copyProperties(a, icReminderDto);
            icReminderDto.setReminderDto(reminderDto);
            return icReminderDto;
        }).collect(Collectors.toList());
        log.info("Exit InitialContact ReadAllICReminder");
        return icReminderDtoList;
    }

    @Override
    public void removeICReminder(Long icReminderId) {
        log.info("Inside InitialContact RemoveICReminder");
        ICReminder icReminder =
                icReminderRepository.findById(icReminderId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(icReminderId))));

        if (icReminder.getStatusOfDeletion().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(icReminderId)));
        }
        icReminder.setStatusOfDeletion("INACTIVE");
        icReminder.getReminder().setStatusOfDeletion("INACTIVE");
        icReminderRepository.save(icReminder);
        log.info("Exit InitialContact RemoveICReminder");
    }

}
