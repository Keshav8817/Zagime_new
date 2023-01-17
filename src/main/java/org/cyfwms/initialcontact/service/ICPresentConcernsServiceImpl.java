package org.cyfwms.initialcontact.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICPresentConcernsDto;
import org.cyfwms.initialcontact.entity.ICPresentConcerns;
import org.cyfwms.initialcontact.repository.ICPresentConcernsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
@Slf4j
public class ICPresentConcernsServiceImpl implements ICPresentConcernsService {
    @Autowired
    ICPresentConcernsRepository iCPresentConcernsRepository;

    @Override
    public ICPresentConcernsDto readPresentConcerns(Long fileDetailsId) {
        log.info("Inside InitialContact ReadPresentConcerns");
        ICPresentConcernsDto iCPresentConcernsDto = new ICPresentConcernsDto();
        if (fileDetailsId != 0) {
            ICPresentConcerns iCPresentConcerns = iCPresentConcernsRepository.findByFileDetailsId(fileDetailsId);
            if (iCPresentConcerns != null) {
                BeanUtils.copyProperties(iCPresentConcerns, iCPresentConcernsDto);
            }
        }
        log.info("Exit InitialContact ReadPresentConcerns");
            return iCPresentConcernsDto;
    }

    @Override
    public ICPresentConcernsDto savePresentConcerns(ICPresentConcernsDto iCPresentConcernsDto) {
        log.info("Inside InitialContact SavePresentConcerns");
        ICPresentConcerns iCPresentConcerns = null;
        if(iCPresentConcernsDto.getPresentConcernsId() == 0) {
            iCPresentConcerns = new ICPresentConcerns();
            BeanUtils.copyProperties(iCPresentConcernsDto, iCPresentConcerns);
            iCPresentConcerns.setStatus("ACTIVE");
        } else {
            iCPresentConcerns = iCPresentConcernsRepository.findById(iCPresentConcernsDto.getPresentConcernsId()).get();
            BeanUtils.copyProperties(iCPresentConcernsDto, iCPresentConcerns);
        }
        iCPresentConcerns = iCPresentConcernsRepository.save(iCPresentConcerns);
        iCPresentConcernsDto.setFileDetailsId(iCPresentConcerns.getFileDetailsId());
        iCPresentConcernsDto.setPresentConcernsId(iCPresentConcerns.getPresentConcernsId());
        log.info("Exit InitialContact SavePresentConcerns");
        return iCPresentConcernsDto;
    }
}
