package org.cyfwms.culturalprogram.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.culturalprogram.dto.CPAParticipantDto;
import org.cyfwms.culturalprogram.entity.CPAParticipant;
import org.cyfwms.culturalprogram.repository.CPAParticipantRepository;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CPAParticipantServiceImpl implements CPAParticipantService {
    @Autowired
    private CPAParticipantRepository CPAParticipantRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public CPAParticipantDto saveCpaParticipant(CPAParticipantDto CPAParticipantDto) {
        log.info("Inside SaveCpaParticipant");
        CPAParticipant CPAParticipant = null;
        if (CPAParticipantDto.getParticipantCulturalProId() == 0) {
            CPAParticipant = new CPAParticipant();
            BeanUtils.copyProperties(CPAParticipantDto, CPAParticipant);
            CPAParticipant.setStatus("ACTIVE");
        } else {
            CPAParticipant = CPAParticipantRepository.findById(CPAParticipantDto.getParticipantCulturalProId()).get();
            BeanUtils.copyProperties(CPAParticipantDto, CPAParticipant);
        }
        CPAParticipant = CPAParticipantRepository.save(CPAParticipant);
        CPAParticipantDto.setParticipantCulturalProId(CPAParticipant.getParticipantCulturalProId());
        log.info("Exit SaveCpaParticipant");
        return CPAParticipantDto;
    }

    @Override
    public CPAParticipantDto readCpaParticipant(Long culturalProgramId) {
        log.info("Inside ReadCpaParticipant");
        CPAParticipantDto CPAParticipantDto = new CPAParticipantDto();
        if (culturalProgramId != 0) {
            CPAParticipant CPAParticipant = CPAParticipantRepository.findByCulturalProgramId(culturalProgramId);
            if (CPAParticipant != null) {
                BeanUtils.copyProperties(CPAParticipant, CPAParticipantDto);
                if (CPAParticipant.getParticipant()!=null) {
                    Participant participant = participantRepository.findByParticipantId(Long.parseLong(CPAParticipantDto.getParticipant()));
                    if (!CPAParticipant.getParticipant().equals("0")) {
                        CPAParticipantDto.setParticipant(participant.getFirstname() + " " + participant.getSurname());
                        CPAParticipantDto.setParticipantId(participant.getParticipantId());
                    }
                }
            }

        }
        log.info("Exit ReadCpaParticipant");
        return CPAParticipantDto;
    }

    @Override
    public void removeCpaParticipant(Long participantCulturalProId) {
        log.info("Inside RemoveCpaParticipant");
        Optional<CPAParticipant> cpaParticipantOpt = CPAParticipantRepository.findById(participantCulturalProId);
        if (cpaParticipantOpt.isPresent()) {
            CPAParticipant CPAParticipant = new CPAParticipant();
            CPAParticipant = cpaParticipantOpt.get();
            CPAParticipant.setStatus("INACTIVE");
            CPAParticipantRepository.save(CPAParticipant);
            log.info("Exit RemoveCpaParticipant");
        }
    }
}
