package org.cyfwms.initialcontact.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.initialcontact.dto.ICFileDetailsDto;
import org.cyfwms.initialcontact.entity.ICFileDetails;
import org.cyfwms.initialcontact.repository.ICFileDetailsRepository;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ICFileDetailsServiceImpl implements ICFileDetailsService {

    @Autowired
    MessageUtil messageUtil;
    @Autowired
    private ICFileDetailsRepository fileDetailsRepo;
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public ICFileDetailsDto readAllFileDetails(Long fileDetailsID) {
        log.info("Inside ReadAllFileDetails InitialContact");
        ICFileDetailsDto iCFileDetailsDto = new ICFileDetailsDto();
        if (fileDetailsID != 0) {
            ICFileDetails iCFileDetails = fileDetailsRepo.findById(
                    fileDetailsID).filter(a -> a.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                    new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(fileDetailsID))));
            if (iCFileDetails != null) {
                BeanUtils.copyProperties(iCFileDetails, iCFileDetailsDto);
                if (iCFileDetails.getClientName() != null) {
                    Participant participant = participantRepository.findByParticipantId(Long.parseLong(iCFileDetailsDto.getClientName()));
                    if (participant != null) {
                        iCFileDetailsDto.setClientName(participant.getFirstname() + " " + participant.getSurname());
                        iCFileDetailsDto.setParticipantId(participant.getParticipantId());
                    }
                }
            }
        }
        log.info("Exit ReadAllFileDetails InitialContact");
        return iCFileDetailsDto;
    }

    @Override
    public ICFileDetailsDto saveAllFileDetails(ICFileDetailsDto iCFileDetailsDto) {
        log.info("Inside SaveAllFileDetails InitialContact");
        ICFileDetails iCFileDetails = null;
        if (iCFileDetailsDto.getFileDetailsId() == 0) {
            iCFileDetails = new ICFileDetails();
            BeanUtils.copyProperties(iCFileDetailsDto, iCFileDetails);
            iCFileDetails.setStatusOfDeletion("ACTIVE");
            Optional<ICFileDetails> initialContactFileDetailOpt = fileDetailsRepo.findTopByOrderByFileNumberDesc();
            if (initialContactFileDetailOpt.isPresent()) {
                ICFileDetails initialContactFileDtls = initialContactFileDetailOpt.get();
                iCFileDetails.setFileNumber(initialContactFileDtls.getFileNumber() + 1L);
            } else {
                iCFileDetails.setFileNumber(1L);
            }
        } else {
            iCFileDetails = fileDetailsRepo.findById(iCFileDetailsDto.getFileDetailsId()).get();
            BeanUtils.copyProperties(iCFileDetailsDto, iCFileDetails);
        }
        iCFileDetails = fileDetailsRepo.save(iCFileDetails);
        iCFileDetailsDto.setFileDetailsId(iCFileDetails.getFileDetailsId());
        iCFileDetailsDto.setFileNumber(iCFileDetails.getFileNumber());
        log.info("Exit SaveAllFileDetails InitialContact");
        return iCFileDetailsDto;
    }

    @Override
    public void removeICFileDetails(Long fileDetailsId) {
        log.info("Inside RemoveICFileDetails InitialContact");
        ICFileDetails iCFileDetails = fileDetailsRepo.findById(fileDetailsId).filter(a->a.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")) .orElseThrow(() -> new NoSuchElementFoundException(
                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                        String.valueOf(fileDetailsId))));
            iCFileDetails.setStatusOfDeletion("INACTIVE");
            fileDetailsRepo.save(iCFileDetails);
            log.info("Exit RemoveICFileDetails InitialContact");

    }
}
