package org.cyfwms.initialcontact.service;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICCommonDataDto;
import org.cyfwms.initialcontact.entity.*;
import org.cyfwms.initialcontact.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
@Service
@Slf4j
public class ICCommonDataServiceImpl implements ICCommonDataService {
    @Autowired
    ICFileDetailsRepository iCFileDetailsRepository;

    @Autowired
    ICIncidentReportRepository iCIncidentReportRepository;
    @Autowired
    ICPatientCareInfoRepository iCPatientCareInfoRepository;
    @Autowired
    ICPresentConcernsRepository iCPresentConcernsRepository;
    @Autowired
    ICReferralInfoRepository iCReferralInfoRepository;
    @Autowired
    ICContactNotesRepository icContactNotesRepository;
    @Override
    public ICCommonDataDto iCCommonData(Long fileNumber) {
        log.info("Inside ICCommonData InitialContact");
        ICCommonDataDto iCCommonDataDto = new ICCommonDataDto();
        if (fileNumber!=0) {
            ICFileDetails iCFileDetails = readFileNumber(fileNumber);
            if (iCFileDetails != null) {
                Long fileDetailsId = iCFileDetails.getFileDetailsId();
                BeanUtils.copyProperties(iCFileDetails, iCCommonDataDto);
                if (iCCommonDataDto.getDateClosed() == null) {
                    iCCommonDataDto.setDateClosed(LocalDate.of(1, 1, 1));
                }

                ICIncidentReport iCtIncidentReport = iCIncidentReportRepository.findByFileDetailsId(fileDetailsId);
                if (iCtIncidentReport != null) {
                    iCCommonDataDto.setIncidentReport(iCtIncidentReport);
                }
                if (iCCommonDataDto.getIncidentReport().getDateOfReport() == null) {
                    iCCommonDataDto.getIncidentReport().setDateOfReport(LocalDate.of(1, 1, 1));
                }
                if (iCCommonDataDto.getIncidentReport().getIncidentDate() == null) {
                    iCCommonDataDto.getIncidentReport().setIncidentDate(LocalDate.of(1, 1, 1));
                }
                if (iCCommonDataDto.getIncidentReport().getIncidentTime() == null) {
                    iCCommonDataDto.getIncidentReport().setIncidentTime(LocalTime.of(1, 1, 1));
                }

                ICPatientCareInfo iCPatientCareInfo = iCPatientCareInfoRepository.findByFileDetailsId(fileDetailsId);
                if (iCPatientCareInfo != null) {
                   iCCommonDataDto.setPatientCareInfo(iCPatientCareInfo);
                }
                ICPresentConcerns iCPresentConcerns = iCPresentConcernsRepository.findByFileDetailsId(fileDetailsId);
                if (iCPresentConcerns != null) {
                    iCCommonDataDto.setPresentConcerns(iCPresentConcerns);
                }
                ICReferralInfo iCReferralInfo = iCReferralInfoRepository.findByFileDetailsId(fileDetailsId);
                if (iCReferralInfo != null) {
                   iCCommonDataDto.setReferralInfo(iCReferralInfo);
                }
                ICContactNotes icContactNotes =icContactNotesRepository.findByFileDetailsId(fileDetailsId);
                if (icContactNotes != null) {
                    iCCommonDataDto.setIcContactNotes(icContactNotes);
                }
            }
        }
        log.info("Exit ICCommonData InitialContact");
            return iCCommonDataDto;


    }

    private ICFileDetails readFileNumber(Long fileNumber) {
        log.info("Inside ReadFileNumber InitialContact");
        ICFileDetails iCFileDetails = null;
        Optional<ICFileDetails> iCFileDetailsOpt = iCFileDetailsRepository.findByFileNumber(fileNumber);
        if (iCFileDetailsOpt.isPresent()) {
            iCFileDetails = iCFileDetailsOpt.get();
        }
        log.info("Exit ReadFileNumber InitialContact");
        return iCFileDetails;
    }
}
