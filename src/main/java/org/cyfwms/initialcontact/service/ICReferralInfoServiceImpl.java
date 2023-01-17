package org.cyfwms.initialcontact.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICReferralInfoDto;
import org.cyfwms.initialcontact.entity.ICReferralInfo;
import org.cyfwms.initialcontact.repository.ICReferralInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
@Slf4j
public class ICReferralInfoServiceImpl implements ICReferralInfoService {
    @Autowired
    ICReferralInfoRepository iCReferralInfoRepository;

    @Override
    public ICReferralInfoDto readAllReferralInfo(Long fileDetailsId) {
        log.info("Inside InitialContact ReadAllReferralInfo");
        ICReferralInfoDto iCReferralInfoDto = new ICReferralInfoDto();
        if (fileDetailsId != 0) {
            ICReferralInfo iCReferralInfo = iCReferralInfoRepository.findByFileDetailsId(fileDetailsId);
            if (iCReferralInfo != null) {
                BeanUtils.copyProperties(iCReferralInfo, iCReferralInfoDto);
            }
        }
        log.info("Exit InitialContact ReadAllReferralInfo");
            return iCReferralInfoDto;
    }

    @Override
    public ICReferralInfoDto saveAllReferralInfo(ICReferralInfoDto iCReferralInfoDto) {
        log.info("Inside InitialContact SaveAllReferralInfo");
        ICReferralInfo iCReferralInfo = null;
        if (iCReferralInfoDto.getReferralInfoId() == 0) {
            iCReferralInfo = new ICReferralInfo();
            BeanUtils.copyProperties(iCReferralInfoDto, iCReferralInfo);
            iCReferralInfo.setStatus("ACTIVE");
        } else {
            iCReferralInfo = iCReferralInfoRepository.findById(iCReferralInfoDto.getReferralInfoId()).get();
            BeanUtils.copyProperties(iCReferralInfoDto, iCReferralInfo);
        }
        iCReferralInfo = iCReferralInfoRepository.save(iCReferralInfo);
        iCReferralInfoDto.setFileDetailsId(iCReferralInfo.getFileDetailsId());
        iCReferralInfoDto.setReferralInfoId(iCReferralInfo.getReferralInfoId());
        log.info("Exit InitialContact SaveAllReferralInfo");
        return iCReferralInfoDto;
    }
}
