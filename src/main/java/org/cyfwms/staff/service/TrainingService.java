package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.TrainingDto;

import java.util.List;

public interface TrainingService {
    TrainingDto saveStaffTraining(TrainingDto trainingDto);
    TrainingDto readStaffTraining(Long trainingId);
    void deleteStaffTraining(Long trainingId);
    List<TrainingDto> readAllStaffTraining(Long staffId);
}
