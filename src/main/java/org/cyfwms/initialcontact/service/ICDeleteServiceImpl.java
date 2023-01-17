package org.cyfwms.initialcontact.service;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.initialcontact.entity.ICPatientCareInfo;
import org.cyfwms.initialcontact.entity.ICPresentConcerns;
import org.cyfwms.initialcontact.entity.ICReferralInfo;
import org.cyfwms.initialcontact.entity.ICIncidentReport;
import org.cyfwms.initialcontact.repository.ICIncidentReportRepository;
import org.cyfwms.initialcontact.repository.ICPatientCareInfoRepository;
import org.cyfwms.initialcontact.repository.ICPresentConcernsRepository;
import org.cyfwms.initialcontact.repository.ICReferralInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class ICDeleteServiceImpl implements ICDeleteService {
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private ICReferralInfoRepository icReferralInfoRepo;
    @Autowired
    private ICIncidentReportRepository icIncidentReportRepo;
    @Autowired
    private ICPresentConcernsRepository icPresentConcernsRepo;
    @Autowired
    private ICPatientCareInfoRepository icPatientCareInfoRepo;

    @Override
    public void removeReferralInfo(Long referralInfoId) {
        log.info("Inside RemoveReferralInfo");
        ICReferralInfo iCReferralInfo =
                icReferralInfoRepo.findById(referralInfoId).filter(a -> a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(referralInfoId))));
        iCReferralInfo.setStatus("INACTIVE");
        log.info("Exit RemoveReferralInfo");
        icReferralInfoRepo.save(iCReferralInfo);
    }

    @Override
    public void removeIncidentReport(Long incidentReportId) {
        log.info("Inside RemoveIncidentReport");
        ICIncidentReport iCIncidentReport =
                icIncidentReportRepo.findById(incidentReportId).filter(a -> a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(incidentReportId))));
        iCIncidentReport.setStatus("INACTIVE");
        log.info("Exit RemoveIncidentReport");
        icIncidentReportRepo.save(iCIncidentReport);
    }

    @Override
    public void removePresentConcerns(Long presentConcernsId) {
        log.info("Inside RemovePresentConcerns");
        ICPresentConcerns iCPresentConcerns =
                icPresentConcernsRepo.findById(presentConcernsId).filter(a -> a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(presentConcernsId))));
        iCPresentConcerns.setStatus("INACTIVE");
        log.info("Exit RemovePresentConcerns");
        icPresentConcernsRepo.save(iCPresentConcerns);
    }

    @Override
    public void removePatientCareInfo(Long patientCareInfoId) {
        log.info("Inside RemovePatientCareInfo");
        ICPatientCareInfo iCPatientCareInfo =
                icPatientCareInfoRepo.findById(patientCareInfoId).filter(a -> a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(patientCareInfoId))));
        iCPatientCareInfo.setStatus("INACTIVE");
        log.info("Exit RemovePatientCareInfo");
        icPatientCareInfoRepo.save(iCPatientCareInfo);
    }
}
