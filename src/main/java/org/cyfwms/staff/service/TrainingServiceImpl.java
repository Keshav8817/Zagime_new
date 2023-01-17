package org.cyfwms.staff.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.StaffDto;
import org.cyfwms.staff.dto.TrainingDto;
import org.cyfwms.staff.entity.Staff;
import org.cyfwms.staff.entity.Training;
import org.cyfwms.staff.repository.TrainingRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    TrainingRepo trainingRepo;
    @Autowired
    private MessageUtil messageUtil;
    @Override
    public TrainingDto saveStaffTraining(TrainingDto trainingDto) {
        log.info("Inside SaveStaffTraining");
        Training training = null;
        if (trainingDto.getTrainingId() == 0) {
            training = new Training();
            BeanUtils.copyProperties(trainingDto, training);
            training.setStatusOfDeletion("ACTIVE");

        } else {
            training = trainingRepo.findById(trainingDto.getTrainingId()).get();
            BeanUtils.copyProperties(trainingDto, training);
        }
        training = trainingRepo.save(training);
        trainingDto.setTrainingId(training.getTrainingId());
        log.info("Exit SaveStaffTraining");
        return trainingDto;
    }

    @Override
    public TrainingDto readStaffTraining(Long trainingId) {
        log.info("Inside ReadStaffTraining");
        TrainingDto trainingDto = new TrainingDto();
        Training training = trainingRepo.findById(
                trainingId).filter(active -> active.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(trainingId))));
        BeanUtils.copyProperties(training, trainingDto);
        log.info("Exit ReadStaffTraining");

        return trainingDto;
    }

    @Override
    public void deleteStaffTraining(Long trainingId) {
        log.info("Inside DeleteStaffTraining");
        Training training =
                trainingRepo.findById(trainingId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(trainingId))));

        if (training.getStatusOfDeletion().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(trainingId)));
        }
        training.setStatusOfDeletion("INACTIVE");
        log.info("Exit RemoveStaff");
        trainingRepo.save(training);
    }

    @Override
    public List<TrainingDto> readAllStaffTraining(Long staffId) {
        List<TrainingDto> trainingDtos = new ArrayList<TrainingDto>();
        trainingDtos = trainingRepo.findByStaffId(staffId)
                .stream()
                .map(training -> {
                    TrainingDto trainingDto = new TrainingDto();
                    BeanUtils.copyProperties(training, trainingDto);
                    return trainingDto;
                }).collect(Collectors.toList());
        return trainingDtos;

    }
}
