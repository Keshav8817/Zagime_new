package org.cyfwms.participant.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.EducationDto;
import org.cyfwms.participant.entity.Education;
import org.cyfwms.participant.repository.EducationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EducationServiceImpl  implements EducationService{
    @Autowired
    private EducationRepository educationRepository;


    @Override
    public EducationDto readEducation(Long participantId) {
        log.info("Inside ReadEducation");
        EducationDto educationDto = new EducationDto();
        if (participantId != 0) {
            Education education = educationRepository.findByParticipantId(participantId);
            if (education != null) {
                BeanUtils.copyProperties(education, educationDto);
            }
        }
        log.info("Exit ReadEducation");
        return educationDto;
    }

    @Override
    public EducationDto saveEducation(EducationDto educationDto) {
        log.info("Inside SaveEducation");
        Education education =  null;
        if (educationDto.getEducationId() == 0) {
            education = new Education();
            BeanUtils.copyProperties(educationDto, education);
        } else {
            education = educationRepository.findById(educationDto.getEducationId()).get();
            BeanUtils.copyProperties(educationDto, education);
        }
        education = educationRepository.save(education);
        educationDto.setEducationId(education.getEducationId());
        log.info("Exit SaveEducation");
        return educationDto;
    }
}
