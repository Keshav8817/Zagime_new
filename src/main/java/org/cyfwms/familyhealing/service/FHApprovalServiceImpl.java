package org.cyfwms.familyhealing.service;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHApprovalDto;
import org.cyfwms.familyhealing.entity.FHApproval;
import org.cyfwms.familyhealing.repository.FHApprovalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@Slf4j
public class FHApprovalServiceImpl implements FHApprovalService{
    @Autowired
    private FHApprovalRepository fHApprovalRepository;
    @Autowired
    private MessageUtil messageUtil;
    @Override
    public FHApprovalDto readApproval(Long fhFileDetailsId) {
        log.info("Inside ReadFHApproval");
        FHApprovalDto fHHistoryDto = new FHApprovalDto();
       if (fhFileDetailsId!=0) {
           Optional<FHApproval> fHApproval = Optional.ofNullable(fHApprovalRepository.findByFhFileDetailsId(fhFileDetailsId).orElseThrow(() ->
                   new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                           String.valueOf(fhFileDetailsId)))));

           BeanUtils.copyProperties(fHApproval.get(), fHHistoryDto);
           log.info("Exit ReadFHApproval");
       }
        return fHHistoryDto;
    }

    @Override
    public FHApprovalDto saveApproval(FHApprovalDto fhApprovalDto) {
        log.info("Inside SaveFHApproval");
        FHApproval fHApproval = null;
        if (fhApprovalDto.getFhApprovalId() == 0) {
            fHApproval = new FHApproval();
            BeanUtils.copyProperties(fhApprovalDto, fHApproval);
            fHApproval.setStatus("ACTIVE");

        } else {
            fHApproval = fHApprovalRepository.findById(fhApprovalDto.getFhApprovalId()).get();
            BeanUtils.copyProperties(fhApprovalDto, fHApproval);
        }
        fHApproval = fHApprovalRepository.save(fHApproval);
        fhApprovalDto.setFhFileDetailsId(fHApproval.getFhFileDetailsId());
        fhApprovalDto.setFhApprovalId(fHApproval.getFhApprovalId());
        log.info("Exit SaveFHApproval");
        return fhApprovalDto;
    }
}
