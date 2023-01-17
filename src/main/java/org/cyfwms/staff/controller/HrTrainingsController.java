package org.cyfwms.staff.controller;

import java.util.List;

import org.cyfwms.staff.dto.TrainingDto;
import org.cyfwms.staff.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j(topic = "HrTrainingsController")
@AllArgsConstructor
@RequestMapping("/v1/hrservice/trainings/")
@CrossOrigin("*")
public class HrTrainingsController {
    @Autowired
    private TrainingService trainingsService;

    @ApiOperation("Save/Set or update one/single training.")
    @PutMapping(value = "save_one", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TrainingDto> saveStaffTraining(
        @RequestBody TrainingDto trainingDto
    ) {
        TrainingDto trainingdto = trainingsService.saveStaffTraining(trainingDto);
        log.info("SaveStaffTraining: " + trainingDto);
        return new ResponseEntity<>(trainingdto, HttpStatus.CREATED);
    }

    @ApiOperation("Read/Get one/single training.")
    @GetMapping(value = "read_one/{trainingId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto readStaffTraining(
        @PathVariable("trainingId") Long trainingId
    ) {
        log.info("ReadStaffTraining: " + trainingId);
        return trainingsService.readStaffTraining(trainingId);
    }

    @ApiOperation("Read/Get all trainings.")
    @GetMapping(value = "read_all/{staffId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDto> readAllStaffTraining(
        @PathVariable("staffId") Long staffId
    ) {
        log.info("ReadAllStaffTraining staffId: " + staffId);
        return trainingsService.readAllStaffTraining(staffId);
    }

    @ApiOperation("Soft delete one/single training.")
    @DeleteMapping("remove_one/{trainingId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeStaffTraining(
        @PathVariable("trainingId") Long trainingId
    ) {
        log.info("RemoveStaffTraining By TrainingId :" + trainingId);
        trainingsService.deleteStaffTraining(trainingId);
    }
}
