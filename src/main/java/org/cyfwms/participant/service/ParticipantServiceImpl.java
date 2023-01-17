package org.cyfwms.participant.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.entity.Attachment;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.common.util.ReferenceIDGeneratorUtil;
import org.cyfwms.participant.dto.ParticipantAttachmentDto;
import org.cyfwms.participant.dto.ParticipantIdentityDto;
import org.cyfwms.participant.entity.Participant;
import org.cyfwms.participant.entity.ParticipantAttachment;
import org.cyfwms.participant.repository.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
@AllArgsConstructor
@Slf4j
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ParticipantAttachmentService participantAttachmentService;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private ReferenceIDGeneratorUtil referenceIDGeneratorUtil;

    @Override
    public ParticipantIdentityDto readParticipantIdentity(Long participantId) {
        log.info("Reading ReadParticipantIdentity");
        ParticipantIdentityDto participantIdentityDto = new ParticipantIdentityDto();
        Participant participant = readParticipant(participantId);
        if (participant.getStatus().equals("ACTIVE")) {
            ParticipantAttachmentDto participantAttachmentDto =
                    new ParticipantAttachmentDto();
            Optional<ParticipantAttachment> participantAttachmentOpt =
                    participant.getParticipantAttachmentList()
                            .stream()
                            .filter(attachment ->
                                    attachment.getAttachmentType().equals("PARTICIPANT_PROFILE_PIC") &&
                                            attachment.getStatus().equals("ACTIVE"))
                            .findFirst();
            if (participantAttachmentOpt.isPresent()) {
                BeanUtils.copyProperties(participantAttachmentOpt.get(),
                        participantAttachmentDto);
            }
            BeanUtils.copyProperties(participant,
                    participantIdentityDto);
            mapParticipantImageData(participantIdentityDto,
                    participantAttachmentDto);
        }
        log.info("Exit ReadParticipantIdentity");
        return participantIdentityDto;
    }

    private Participant readParticipant(Long participantId) {
        Participant participant = participantRepository.findById(
                participantId).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(participantId))));
        return participant;
    }

    private void mapParticipantImageData(
            ParticipantIdentityDto participantIdentityDto,
            ParticipantAttachmentDto participantAttachmentDto) {
        if (participantAttachmentDto.getAttachment() != null) {
            participantIdentityDto.setParticipantImageId(
                    participantAttachmentDto.getParticipantAttachmentId());
            participantIdentityDto.setParticipantImageName(
                    participantAttachmentDto.getAttachment().getAttachmentName());
            participantIdentityDto.setImage(
                    participantAttachmentDto.getAttachment().getAttachmentContents());
            participantIdentityDto.setParticipantImageType(
                    participantAttachmentDto.getAttachmentType());
        }
    }

    @Override
    public ParticipantIdentityDto saveParticipantIdentity(ParticipantIdentityDto participantIdentityDto, MultipartFile multipartFile) throws IOException {
        log.info("Inside SaveParticipantIdentity");
        ParticipantAttachment participantAttachment =
                new ParticipantAttachment();

        ParticipantAttachmentDto participantAttachmentDto = null;
        Participant participant = null;
        List<ParticipantAttachment> participantAttachmentList = null;
        if (multipartFile != null) {
            validateParticipantImage(multipartFile);
            participantAttachmentDto =
                    populateParticipantAttachmentData(multipartFile);
            if (participantAttachmentDto != null) {
                BeanUtils.copyProperties(participantAttachmentDto,
                        participantAttachment);
                participantAttachmentList = new ArrayList<>();
                participantAttachmentList.add(participantAttachment);
            }
        }
        long participantID = participantIdentityDto.getParticipantId();
        if (participantID == 0) {
            participant = new Participant();
            BeanUtils.copyProperties(participantIdentityDto, participant);
            participant.setType("CYFM");
            participant.setStatus("ACTIVE");
            participant.setReferenceId(
                    referenceIDGeneratorUtil.generateParticipantReferenceID());
        } else {
            participant = readParticipant(participantID);
            BeanUtils.copyProperties(participantIdentityDto, participant);
            participant.setParticipantId(participantID);

            if (participantAttachmentList != null) {
                for (int i = 0; i < participant.getParticipantAttachmentList().size(); i++) {
                    participantAttachmentDto.setParticipantAttachmentId(participant.getParticipantAttachmentList().get(i).getParticipantAttachmentId());
                    participantAttachmentDto.getAttachment().setAttachmentId(participant.getParticipantAttachmentList().get(i).getAttachment().getAttachmentId());
                    participantAttachmentDto.getAttachment().setReceiptDate(participant.getParticipantAttachmentList().get(i).getAttachment().getReceiptDate());
                }
                BeanUtils.copyProperties(participantAttachmentDto,
                        participantAttachment);
            }

            if (participantIdentityDto.getRemoveProfilePicture().equals(true)) {
                participantAttachmentDto = removeProfilePicture(participant);
                BeanUtils.copyProperties(participantAttachmentDto, participantAttachment);
                participantAttachmentList = new ArrayList<>();
                participantAttachmentList.add(participantAttachment);
            }
        }
        if (participantAttachmentList != null) {
            participant.setParticipantAttachmentList(participantAttachmentList);
        }
        participant = participantRepository.save(participant);
        participantIdentityDto.setParticipantId(participant.getParticipantId());
        participantIdentityDto.setReferenceId(participant.getReferenceId());
        log.info("Exit SaveParticipantIdentity");
        return participantIdentityDto;
    }

    private ParticipantAttachmentDto removeProfilePicture(Participant participant) {
        ParticipantAttachmentDto participantAttachmentDto = null;
        participantAttachmentDto = new ParticipantAttachmentDto();
        participantAttachmentDto.setStatus("INACTIVE");
        for (int i = 0; i < participant.getParticipantAttachmentList().size(); i++) {
            participantAttachmentDto.setParticipantAttachmentId(participant.getParticipantAttachmentList().get(i).getParticipantAttachmentId());
            participantAttachmentDto.setName(participant.getParticipantAttachmentList().get(i).getName());
            participantAttachmentDto.setType(participant.getParticipantAttachmentList().get(i).getType());
            participantAttachmentDto.setAttachmentType(participant.getParticipantAttachmentList().get(i).getAttachmentType());
        }
        return participantAttachmentDto;
    }

    private void validateParticipantImage(MultipartFile multipartFile) {
        boolean invalidParticipantImage = true;
        if (multipartFile.getContentType().equals("image/jpg")
                || multipartFile.getContentType().equals("image/png")
                || multipartFile.getContentType().equals("image/jpeg")) {
            invalidParticipantImage = false;
        }
        if (invalidParticipantImage) {
            throw new ResponseStatusException(
                    INTERNAL_SERVER_ERROR, "JPG PNG and JPEG content type are allowed");
        }
    }


    private ParticipantAttachmentDto populateParticipantAttachmentData(
            MultipartFile multipartFile) throws IOException {
        ParticipantAttachmentDto participantAttachmentDto = null;
        if (multipartFile != null) {
            participantAttachmentDto = new ParticipantAttachmentDto();
            Attachment attachment = new Attachment();
            attachment.setAttachmentName(multipartFile.getOriginalFilename());
            attachment.setDocumentType(multipartFile.getContentType());
            attachment.setAttachmentContents(multipartFile.getBytes());
            attachment.setAttachmentStatus("ACTIVE");
            participantAttachmentDto.setType(multipartFile.getContentType());
            participantAttachmentDto.setName(multipartFile.getOriginalFilename());
            participantAttachmentDto.setAttachment(attachment);
            participantAttachmentDto.setAttachmentType("PARTICIPANT_PROFILE_PIC");
            participantAttachmentDto.setStatus("ACTIVE");

        }
        return participantAttachmentDto;
    }

    @Override
    public void removeParticipant(Long referenceId) {
        log.info("Inside RemoveParticipant");
        Participant p =
                participantRepository.findByReferenceId(referenceId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(referenceId))));

        if (p.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(referenceId)));
        }
        p.setStatus("INACTIVE");
        log.info("Exit RemoveParticipant");
        participantRepository.save(p);
    }
}
