package org.cyfwms.participant.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.ParticipantContactDto;
import org.cyfwms.participant.entity.ParticipantContact;
import org.cyfwms.participant.repository.ParticipantContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
@Slf4j
public class ParticipantContactServiceImpl implements ParticipantContactService {
    @Autowired
    private ParticipantContactRepository participantContactRepository;

    @Override
    public ParticipantContactDto readParticipantContact(Long participantId) {
        log.info("Inside ReadParticipantContact");
        ParticipantContactDto participantContactDto = new ParticipantContactDto();
        if (participantId != 0) {
            ParticipantContact participantContact = participantContactRepository.findByParticipantId(participantId);
            if (participantContact != null) {
                BeanUtils.copyProperties(participantContact, participantContactDto);
            }
        }
        log.info("Exit ReadParticipantContact");
        return participantContactDto;
    }

    @Override
    public ParticipantContactDto saveParticipantContact(ParticipantContactDto participantContactDto) {
        log.info("Inside SaveParticipantContact");
        ParticipantContact participantContact = null;
        if (participantContactDto.getParticipantContactId() == 0) {
            participantContact = new ParticipantContact();
            BeanUtils.copyProperties(participantContactDto, participantContact);
            participantContact.setStatus("ACTIVE");
        } else {
            participantContact = participantContactRepository.findById(participantContactDto.getParticipantContactId()).get();
            BeanUtils.copyProperties(participantContactDto, participantContact);
        }
        participantContact = participantContactRepository.save(participantContact);
        participantContactDto.setParticipantContactId(participantContact.getParticipantContactId());
        log.info("Exit SaveParticipantContact");
        return participantContactDto;
    }
}
