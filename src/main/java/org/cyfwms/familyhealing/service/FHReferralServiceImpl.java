package org.cyfwms.familyhealing.service;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.dto.FHReferralDto;
import org.cyfwms.familyhealing.entity.FHReferral;
import org.cyfwms.familyhealing.repository.FHReferralRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@Slf4j
public class FHReferralServiceImpl implements FHReferralService {
    @Autowired
    private FHReferralRepository fHReferralRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public FHReferralDto readReferral(Long fhFileDetailsId) {
        log.info("Inside ReadFHReferral");
        FHReferralDto fHReferralDto = new FHReferralDto();
        if (fhFileDetailsId!=0) {
            Optional<FHReferral> fHReferral = Optional.ofNullable(fHReferralRepository.findByFhFileDetailsId(fhFileDetailsId).orElseThrow(() ->
                    new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(fhFileDetailsId)))));

            BeanUtils.copyProperties(fHReferral.get(), fHReferralDto);
            log.info("Exit ReadFHReferral");
        }
        return fHReferralDto;
    }

    @Override
    public FHReferralDto saveReferral(FHReferralDto fhReferralDto) {
        log.info("Inside SaveFHReferral");
        FHReferral fHReferral = null;
        if (fhReferralDto.getFhReferralId() == 0) {
            fHReferral = new FHReferral();
            BeanUtils.copyProperties(fhReferralDto, fHReferral);
            fHReferral.setStatus("ACTIVE");

        } else {
            fHReferral = fHReferralRepository.findById(fhReferralDto.getFhReferralId()).get();
            BeanUtils.copyProperties(fhReferralDto, fHReferral);
        }
        fHReferral = fHReferralRepository.save(fHReferral);
        fhReferralDto.setFhFileDetailsId(fHReferral.getFhFileDetailsId());
        fhReferralDto.setFhReferralId(fHReferral.getFhReferralId());
        log.info("Exit SaveFHReferral");
        return fhReferralDto;
    }
}
