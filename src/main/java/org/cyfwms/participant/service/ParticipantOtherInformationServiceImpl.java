package org.cyfwms.participant.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.ParticipantOtherInformationServiceDto;
import org.cyfwms.participant.entity.ParticipantOtherInformation;
import org.cyfwms.participant.repository.ParticipantOtherInformationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ParticipantOtherInformationServiceImpl implements ParticipantOtherInformationService {
    @Autowired
    private ParticipantOtherInformationRepository participantOtherInformationRepository;

    @Override
    public ParticipantOtherInformationServiceDto readParticipantOtherInformation(Long participantId) {
        log.info("Inside ReadParticipantOtherInformation");
        ParticipantOtherInformationServiceDto participantOtherInformationServiceDto = new ParticipantOtherInformationServiceDto();
        if (participantId != 0) {
            ParticipantOtherInformation participantOtherInformation = participantOtherInformationRepository.findByParticipantId(participantId);
            if (participantOtherInformation != null) {
                BeanUtils.copyProperties(participantOtherInformation, participantOtherInformationServiceDto);
            }
        }
        log.info("Exit ReadParticipantOtherInformation");
        return participantOtherInformationServiceDto;
    }

    @Override
    public ParticipantOtherInformationServiceDto saveParticipantOtherInformation(ParticipantOtherInformationServiceDto participantOtherInformationServiceDto) {
        log.info("Inside SaveParticipantOtherInformation");
        ParticipantOtherInformation participantOtherInformation = null;
        if (participantOtherInformationServiceDto.getParticipantOtherInfoId() == 0) {
            participantOtherInformation = new ParticipantOtherInformation();
            BeanUtils.copyProperties(participantOtherInformationServiceDto, participantOtherInformation);
            participantOtherInformation.setStatus("ACTIVE");
        } else {
            participantOtherInformation = participantOtherInformationRepository.findById(participantOtherInformationServiceDto.getParticipantOtherInfoId()).get();
            BeanUtils.copyProperties(participantOtherInformationServiceDto, participantOtherInformation);
        }
        participantOtherInformation = participantOtherInformationRepository.save(participantOtherInformation);
        participantOtherInformationServiceDto.setParticipantOtherInfoId(participantOtherInformation.getParticipantOtherInfoId());
        log.info("Exit SaveParticipantOtherInformation");
        return participantOtherInformationServiceDto;
    }
}
