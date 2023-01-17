package org.cyfwms.familyhealing.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.familyhealing.entity.FHApproval;
import org.cyfwms.familyhealing.entity.FHHistory;
import org.cyfwms.familyhealing.entity.FHReferral;
import org.cyfwms.familyhealing.repository.FHApprovalRepository;
import org.cyfwms.familyhealing.repository.FHHistoryRepository;
import org.cyfwms.familyhealing.repository.FHReferralRepository;
import org.cyfwms.initialcontact.entity.ICReferralInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FHDeleteServiceImpl implements FHDeleteService{
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private FHReferralRepository fhReferralRepo;
    @Autowired
    private FHHistoryRepository fhHistoryRepo;
    @Autowired
    private FHApprovalRepository fhApprovalRepo;
    @Override
    public void removeReferral(Long fhReferralId) {
        log.info("Inside RemoveReferral");
        FHReferral fHReferral =
                fhReferralRepo.findById(fhReferralId).filter(a -> a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhReferralId))));
        fHReferral.setStatus("INACTIVE");
        log.info("Exit RemoveReferral");
        fhReferralRepo.save(fHReferral);
    }

    @Override
    public void removeApproval(Long fhApprovalId) {
        log.info("Inside RemoveApproval");
        FHApproval fHApproval =
                fhApprovalRepo.findById(fhApprovalId).filter(a -> a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhApprovalId))));
        fHApproval.setStatus("INACTIVE");
        log.info("Exit RemoveApproval");
        fhApprovalRepo.save(fHApproval);
    }

    @Override
    public void removeHistory(Long fhHistoryId) {
        log.info("Inside RemoveHistory");
        FHHistory fHHistory =
                fhHistoryRepo.findById(fhHistoryId).filter(a -> a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(fhHistoryId))));
        fHHistory.setStatus("INACTIVE");
        log.info("Exit RemoveHistory");
        fhHistoryRepo.save(fHHistory);
    }
}
