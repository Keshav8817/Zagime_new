package org.cyfwms.initialcontact.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICParticipantDto;
import org.cyfwms.initialcontact.entity.ICParticipant;
import org.cyfwms.initialcontact.repository.ICParticipantRepository;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class ICParticipantServiceImpl implements ICParticipantService {
    @Autowired
    ICParticipantRepository icParticipantRepository;
    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public ICParticipantDto saveICParticipant(ICParticipantDto iCParticipantDto) {
        log.info("Inside InitialContact SaveICParticipant");
        ICParticipant iCParticipant = null;
        if (iCParticipantDto.getIcParticipantId() == 0) {
            iCParticipant = new ICParticipant();
            BeanUtils.copyProperties(iCParticipantDto, iCParticipant);
            iCParticipant.setStatus("ACTIVE");
        } else {
            iCParticipant = icParticipantRepository.findById(iCParticipantDto.getIcParticipantId()).get();
            BeanUtils.copyProperties(iCParticipantDto, iCParticipant);
        }
        iCParticipant = icParticipantRepository.save(iCParticipant);
        iCParticipantDto.setIcParticipantId(iCParticipant.getIcParticipantId());
        log.info("Exit InitialContact SaveICParticipant");
        return iCParticipantDto;
    }

    @Override
    public ICParticipantDto readICParticipant(Long icParticipantId) {


        log.info("Inside InitialContact ReadICParticipant");
        ICParticipantDto iCParticipantDto = new ICParticipantDto();
        if (icParticipantId != 0) {
            Optional<ICParticipant> iCParticipant = icParticipantRepository.findById(icParticipantId)
                    .filter(i -> i.getStatus().equalsIgnoreCase("ACTIVE"));
            if (iCParticipant.isPresent()) {

                BeanUtils.copyProperties(iCParticipant.get(), iCParticipantDto);
                if(iCParticipant.get().getParticipant()!=null) {
                    Participant participant = participantRepository
                            .findByParticipantId(Long.parseLong(iCParticipantDto.getParticipant()));
                    if (!iCParticipant.get().getParticipant().equals("0")) {
                        iCParticipantDto.setParticipant(participant.getFirstname() + " " + participant.getSurname());
                        iCParticipantDto.setParticipantId(participant.getParticipantId());
                    }
                }
            }
        }
        log.info("Exit InitialContact ReadICParticipant");
        return iCParticipantDto;
    }

    @Override
    public void removeICParticipant(Long icParticipantId) {
        log.info("Inside InitialContact RemoveICParticipant");
        Optional<ICParticipant> iCParticipantOpt = icParticipantRepository.findById(icParticipantId);
        if (iCParticipantOpt.isPresent()) {
            ICParticipant iCParticipant = iCParticipantOpt.get();
            iCParticipant.setStatus("INACTIVE");
            icParticipantRepository.save(iCParticipant);
            log.info("Exit InitialContact RemoveICParticipant");
        }
    }
}
