package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICIncidentReportDto;

public interface ICIncidentReportService {
    ICIncidentReportDto readAllIncidentReports(Long fileDetailsID);
    ICIncidentReportDto saveAllIncidentReports(ICIncidentReportDto initialContactIncidentReportDto);
}
