package org.cyfwms.initialcontact.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICIncidentReportDto;
import org.cyfwms.initialcontact.entity.ICIncidentReport;
import org.cyfwms.initialcontact.repository.ICIncidentReportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ICIncidentReportServiceImpl implements ICIncidentReportService {
    @Autowired
    ICIncidentReportRepository iCIncidentReportRepository;

    @Override
    public ICIncidentReportDto readAllIncidentReports(Long fileDetailsID) {
        log.info("Inside InitialContact ReadAllIncidentReports");
        ICIncidentReportDto iCIncidentReportDto = new ICIncidentReportDto();
        if (fileDetailsID != 0) {

            ICIncidentReport iCtIncidentReport = iCIncidentReportRepository.findByFileDetailsId(fileDetailsID);
            if (iCtIncidentReport != null) {
                BeanUtils.copyProperties(iCtIncidentReport, iCIncidentReportDto);
            }

        }
        log.info("Exit InitialContact ReadAllIncidentReports");
            return iCIncidentReportDto;


    }

    @Override
    public ICIncidentReportDto saveAllIncidentReports(ICIncidentReportDto iCIncidentReportDto) {
        log.info("Inside InitialContact SaveAllIncidentReports");
        ICIncidentReport iCtIncidentReport = null;
        if (iCIncidentReportDto.getIncidentReportId() == 0) {
            iCtIncidentReport = new ICIncidentReport();
            BeanUtils.copyProperties(iCIncidentReportDto, iCtIncidentReport);
            iCtIncidentReport.setStatus("ACTIVE");
        } else {
            iCtIncidentReport = iCIncidentReportRepository.findById(iCIncidentReportDto.getIncidentReportId()).get();
            BeanUtils.copyProperties(iCIncidentReportDto, iCtIncidentReport);
        }
        iCtIncidentReport = iCIncidentReportRepository.save(iCtIncidentReport);
        iCIncidentReportDto.setFileDetailsId(iCtIncidentReport.getFileDetailsId());
        iCIncidentReportDto.setIncidentReportId(iCtIncidentReport.getIncidentReportId());
        log.info("Exit InitialContact SaveAllIncidentReports");
        return iCIncidentReportDto;
    }
}
