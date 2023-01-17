package org.cyfwms.culturalprogram.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.culturalprogram.dto.CPAIdentityDto;
import org.cyfwms.culturalprogram.entity.AttachmentEntity;
import org.cyfwms.culturalprogram.entity.CPAIdentity;
import org.cyfwms.culturalprogram.entity.CPAParticipant;
import org.cyfwms.culturalprogram.repository.AttachmentsRepository;
import org.cyfwms.culturalprogram.repository.CPAIdentityRepository;
import org.cyfwms.culturalprogram.repository.CPAParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class CPAIdentityServiceImpl implements CPAIdentityService {

    @Autowired
    private CPAIdentityRepository CPAIdentityRepository;
    @Autowired
    private CPAParticipantRepository CPAParticipantRepository;
    @Autowired
    private AttachmentsRepository attachmentsRepository;

    private CPAIdentity readCulturalProgram(Long culturalProgramId) {
        log.info("Inside CPAIdentity ReadCulturalProgram");
        CPAIdentity CPAIdentity = null;
        Optional<CPAIdentity> culturalProgAndActOpt = CPAIdentityRepository.findById(culturalProgramId);
        if (culturalProgAndActOpt.isPresent()) {
            CPAIdentity = culturalProgAndActOpt.get();
        }
        log.info("Exit CPAIdentity ReadCulturalProgram");
        return CPAIdentity;
    }

    @Override
    public CPAIdentityDto saveCpaIdentity(CPAIdentityDto CPAIdentityDto) {
        log.info("Inside CPAIdentity SaveCulturalProgramIdentity");
        CPAIdentity CPAIdentity = null;
        if (CPAIdentityDto.getCulturalProgramId() == 0) {
            CPAIdentity = new CPAIdentity();
            BeanUtils.copyProperties(CPAIdentityDto, CPAIdentity);
            CPAIdentity.setStatusOfDeletion("ACTIVE");
            Optional<CPAIdentity> culturalProgAndActDetailsOpt = CPAIdentityRepository.findTopByOrderByReferenceIdDesc();
            if (culturalProgAndActDetailsOpt.isPresent()) {
                CPAIdentity CPAIdentityDtls = culturalProgAndActDetailsOpt.get();
                CPAIdentity.setReferenceId(CPAIdentityDtls.getReferenceId() + 128L);
            } else {
                CPAIdentity.setReferenceId(128L);
            }
        } else {
            CPAIdentity = readCulturalProgram(CPAIdentityDto.getCulturalProgramId());
            BeanUtils.copyProperties(CPAIdentityDto, CPAIdentity);
        }
        CPAIdentity = CPAIdentityRepository.save(CPAIdentity);
        CPAIdentityDto.setCulturalProgramId(CPAIdentity.getCulturalProgramId());
        CPAIdentityDto.setReferenceId(CPAIdentity.getReferenceId());
        log.info("Exit CPAIdentity SaveCulturalProgramIdentity");
        return CPAIdentityDto;
    }

    @Override
    public CPAIdentityDto readCpaIdentity(Long culturalProgramId) {
        log.info("Inside CPAIdentity ReadCpaIdentity");
        CPAIdentityDto CPAIdentityDto = new CPAIdentityDto();
        if (culturalProgramId != 0) {
            CPAIdentity CPAIdentity = readCulturalProgram(culturalProgramId);
            if (CPAIdentity != null) {
                BeanUtils.copyProperties(CPAIdentity, CPAIdentityDto);
            }
        }
        log.info("Exit CPAIdentity ReadCpaIdentity");
        return CPAIdentityDto;

    }

    @Override
    public void removeCpaIdentity(Long culturalProgramId) {
        log.info("Inside CPAIdentity RemoveCpaIdentity");
        if (culturalProgramId != 0) {
            Optional<CPAIdentity> CPAIdentity = CPAIdentityRepository.findById(culturalProgramId);
            if (CPAIdentity.isPresent()) {
                CPAIdentity.get().setStatusOfDeletion("INACTIVE");
                CPAIdentityRepository.save(CPAIdentity.get());

                List<CPAParticipant> participants = CPAParticipantRepository.findByculturalProgramId(culturalProgramId).stream().map(cpaParticipant -> {
                    CPAParticipant cPAParticipant = new CPAParticipant();
                    BeanUtils.copyProperties(cpaParticipant,cPAParticipant);
                    cPAParticipant.setStatus("INACTIVE");
                    CPAParticipantRepository.save(cPAParticipant);
                    return cPAParticipant;
                }).collect(Collectors.toList());

                List<AttachmentEntity> attachment = attachmentsRepository.findByCulturalProgramId(culturalProgramId).stream().map(attach -> {
                    AttachmentEntity attachmentEntity = new AttachmentEntity();
                    BeanUtils.copyProperties(attach,attachmentEntity);
                    attachmentEntity.setStatus("INACTIVE");
                    attachmentsRepository.save(attachmentEntity);
                    return attachmentEntity;
                }).collect(Collectors.toList());
                log.info("Exit CPAIdentity RemoveCpaIdentity");
            }
        }
    }

}
