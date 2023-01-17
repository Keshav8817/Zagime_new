package org.cyfwms.familyhealing.service;

import org.cyfwms.familyhealing.dto.FHProgressReportDto;

import java.util.List;

public interface FHProgressReportService {
    FHProgressReportDto saveProgressReport(FHProgressReportDto fhProgressReportDto);

    FHProgressReportDto readProgressReport(Long fhProgressReportId);

    List<FHProgressReportDto> readAllProgressReport(Long fhFileDetailsId);

    void removeProgressReport(Long fhProgressReportId);
}
