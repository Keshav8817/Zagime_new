package org.cyfwms.familyhealing.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.common.util.FileNumberGenerator;
import org.cyfwms.familyhealing.dto.FHFileDetailsDto;
import org.cyfwms.familyhealing.entity.FHFileDetails;
import org.cyfwms.familyhealing.repository.FHFileDetailsRepository;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
@Slf4j
public class FHFileDetailsServiceImpl implements FHFileDetailsService {
    @Autowired
    private FHFileDetailsRepository staffRepository;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private FileNumberGenerator fileNumberGeneratorUtil;
    @Autowired
    private ParticipantRepository participantRepo;

    @Override
    public FHFileDetailsDto readFileDetails(Long fhFileDetailsId) {
        log.info("Inside ReadFHFileDetails");
        FHFileDetailsDto fHFileDetailsDto = new FHFileDetailsDto();
        if (fhFileDetailsId!=0) {
            FHFileDetails fHFileDetails = staffRepository.findById(
                    fhFileDetailsId).filter(active -> active.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                    new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(fhFileDetailsId))));
            if (fHFileDetails.getClientName() != null) {
                Participant participant = participantRepo.findByParticipantId(Long.parseLong(fHFileDetails.getClientName()));
                if (participant != null) {
                    fHFileDetails.setClientName(participant.getFirstname() + " " + participant.getSurname());
                    fHFileDetailsDto.setParticipantId(participant.getParticipantId());
                } else {
                    fHFileDetails.setClientName(null);
                }
            }

            BeanUtils.copyProperties(fHFileDetails, fHFileDetailsDto);
            log.info("Exit ReadFHFileDetails");
        }
        return fHFileDetailsDto;
    }

    @Override
    public FHFileDetailsDto saveFileDetails(FHFileDetailsDto fhFileDetailsDto) {
        log.info("Inside SaveFHFileDetails");
        FHFileDetails fHFileDetails = null;
        if (fhFileDetailsDto.getFhFileDetailsId() == 0) {
            fHFileDetails = new FHFileDetails();
            BeanUtils.copyProperties(fhFileDetailsDto, fHFileDetails);
            fHFileDetails.setStatusOfDeletion("ACTIVE");
            fHFileDetails.setFileNo(
                    fileNumberGeneratorUtil.generateFHFileDetailsFileNo());
        } else {
            fHFileDetails = staffRepository.findById(fhFileDetailsDto.getFhFileDetailsId()).get();
            BeanUtils.copyProperties(fhFileDetailsDto, fHFileDetails);
        }
        fHFileDetails = staffRepository.save(fHFileDetails);
        fhFileDetailsDto.setFhFileDetailsId(fHFileDetails.getFhFileDetailsId());
        fhFileDetailsDto.setFileNo(fHFileDetails.getFileNo());
        log.info("Exit SaveFHFileDetails");
        return fhFileDetailsDto;
    }

    @Override
    public void removeFileDetails(Long fhDetailsId) {
        log.info("Inside RemoveFHFileDetails");
        FHFileDetails s =
                staffRepository.findById(fhDetailsId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhDetailsId))));

        if (s.getStatusOfDeletion().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhDetailsId)));
        }
        s.setStatusOfDeletion("INACTIVE");
        log.info("Exit RemoveFHFileDetails");
        staffRepository.save(s);
    }
}
