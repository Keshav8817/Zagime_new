package org.cyfwms.participant.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.dto.ReminderDto;
import org.cyfwms.common.entity.Reminder;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.common.repository.ReminderRepository;
import org.cyfwms.common.util.FrequencyGeneratorUtil;
import org.cyfwms.participant.dto.ParticipantReminderDto;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.entity.ParticipantReminder;
import org.cyfwms.participant.repository.ParticipantReminderRepository;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class ParticipantReminderServiceImpl implements ParticipantReminderService {
    @Autowired
    FrequencyGeneratorUtil frequencyGeneratorUtil;
    @Autowired
    private ParticipantReminderRepository participantReminderRepository;
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private MessageUtil messageUtil;

    @Override
    public ParticipantReminderDto readParticipantReminder(Long participantReminderId) {
        log.info("Inside ReadParticipantReminder");
        ParticipantReminderDto participantReminderDto = new ParticipantReminderDto();
        ReminderDto reminderDto = new ReminderDto();
        if (participantReminderId != 0) {
            Optional<ParticipantReminder> participantReminder = participantReminderRepository.findById(participantReminderId);

            if (participantReminder.isPresent()) {
                if (participantReminder.get().getStatusOfDeletion().equalsIgnoreCase("ACTIVE")) {
                    BeanUtils.copyProperties(participantReminder.get(), participantReminderDto);
                    BeanUtils.copyProperties(participantReminder.get().getReminder(), reminderDto);
                    Long participantId = Long.parseLong(reminderDto.getRegarding());
                    if (participantId != 0) {
                        Participant participant = participantRepository.findByParticipantId(participantId);
                        reminderDto.setRegarding(participant.getFirstname() + " " + participant.getSurname());
                        reminderDto.setParticipantId(participant.getParticipantId());
                    }
                    participantReminderDto.setReminderDto(reminderDto);

                }
            }
        }
        log.info("Exit ReadParticipantReminder");
        return participantReminderDto;
    }

    @Override
    public List<ParticipantReminderDto> saveParticipantReminder(ParticipantReminderDto participantReminderDto) {
        log.info("Inside SaveParticipantReminder");
        Reminder reminder = null;
        ReminderDto reminderDto = new ReminderDto();
        ParticipantReminderDto participantReminderDto1 = new ParticipantReminderDto();
        List<ParticipantReminderDto> participantReminderDtoList = new ArrayList<>();
        ParticipantReminder participantReminder = new ParticipantReminder();
        if (participantReminderDto.getParticipantReminderId() == 0) {
            if (!participantReminderDto.getReminderDto().getFrequency().isEmpty()) {
                participantReminderDtoList = checkFrequency(participantReminderDto);
                return participantReminderDtoList;
            }
            reminder = new Reminder();
            BeanUtils.copyProperties(participantReminderDto.getReminderDto(), reminder);
            BeanUtils.copyProperties(participantReminderDto, participantReminder);
            participantReminder.setStatusOfDeletion("ACTIVE");
            reminder.setStatusOfDeletion("ACTIVE");

        } else {
            participantReminder = participantReminderRepository.findById(participantReminderDto.getParticipantReminderId()).get();
            reminder = reminderRepository.findById(participantReminderDto.getReminderDto().getReminderId()).get();
            BeanUtils.copyProperties(participantReminderDto.getReminderDto(), reminder);

            BeanUtils.copyProperties(participantReminderDto, participantReminder);

        }

        participantReminder.setReminder(reminder);
        participantReminder = participantReminderRepository.save(participantReminder);

        BeanUtils.copyProperties(participantReminder, participantReminderDto1);
        BeanUtils.copyProperties(reminder, reminderDto);
        participantReminderDto1.setReminderDto(reminderDto);
        participantReminderDtoList.add(participantReminderDto1);
        log.info("Exit SaveParticipantReminder");
        return participantReminderDtoList;
    }

    public List<ParticipantReminderDto> checkFrequency(ParticipantReminderDto participantReminderDto) {
        log.info("Inside CheckFrequency");
        Period pd = Period.between(participantReminderDto.getReminderDto().getReminderDate(), participantReminderDto.getReminderDto().getEndDate());
        int difference = pd.getDays();
        int monthDiff = pd.getMonths();
        int n = 0, counter = 0, rem = 0;
        if (participantReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Daily")) {
            n = difference + 1;
            rem = n + 1;
            counter = 1;
        } else if (participantReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Weekly")) {
            n = difference / 7;
            rem = n % 7;
            n = n + 1;
            counter = 7;
        } else if (participantReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Monthly")) {
            n = monthDiff + 1;
            counter = 31;
        } else {
            n = difference / 14;
            rem = n % 14;
            n = n + 1;
            counter = 14;


        }
        List<ParticipantReminderDto> listparticipantReminder = new ArrayList<>();
        listparticipantReminder = saveFrequency(n, counter, rem, participantReminderDto);
        return listparticipantReminder;
    }

    public List<ParticipantReminderDto> saveFrequency(int n, int counter, int rem, ParticipantReminderDto participantReminderDto) {
        log.info("Inside SaveFrequency");
        List<ParticipantReminderDto> listParticipantReminderDto = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            ParticipantReminder participantReminder = new ParticipantReminder();
            Reminder reminder = new Reminder();
            BeanUtils.copyProperties(participantReminderDto, participantReminder);
            BeanUtils.copyProperties(participantReminderDto.getReminderDto(), reminder);
            participantReminder.setStatusOfDeletion("ACTIVE");
            reminder.setStatusOfDeletion("ACTIVE");
            LocalDate localDate = participantReminderDto.getReminderDto().getReminderDate().plusDays(cnt);
            reminder.setReminderDate(localDate);

            participantReminder.setReminder(reminder);
            participantReminder = participantReminderRepository.save(participantReminder);

            ReminderDto reminderDto = new ReminderDto();
            ParticipantReminderDto participantReminderdto = new ParticipantReminderDto();

            BeanUtils.copyProperties(participantReminder, participantReminderdto);
            BeanUtils.copyProperties(reminder, reminderDto);
            participantReminderdto.setReminderDto(reminderDto);

            listParticipantReminderDto.add(participantReminderdto);
            if (i == n - 1 && rem > 0) {
                cnt = cnt + 1;
            }
            if (participantReminderDto.getReminderDto().getFrequency().equalsIgnoreCase("Monthly")) {
                int year = participantReminderDto.getReminderDto().getReminderDate().getYear();
                int month = participantReminderdto.getReminderDto().getReminderDate().getMonth().getValue();
                int monthly = frequencyGeneratorUtil.generateMonthlyUtil(month, cnt, year, counter);
                cnt = monthly;
            } else {
                cnt = cnt + counter;
            }
        }

        return listParticipantReminderDto;
    }

    @Override
    public void removeParticipantReminder(Long participantReminderId) {
        log.info("Inside RemoveParticipantReminder");
        ParticipantReminder p =
                participantReminderRepository.findById(participantReminderId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(participantReminderId))));

        if (p.getStatusOfDeletion().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(participantReminderId)));
        }
        p.setStatusOfDeletion("INACTIVE");
        p.getReminder().setStatusOfDeletion("INACTIVE");
        participantReminderRepository.save(p);
        log.info("Exit RemoveParticipantReminder");
    }
}