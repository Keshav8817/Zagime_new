package org.cyfwms.staff.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.staff.dto.GoalsAndObjectivesDto;
import org.cyfwms.staff.service.GoalsAndObjectivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j(topic = "GoalsAndObjectivesController")
@AllArgsConstructor
@RequestMapping("/v1/staff")
@CrossOrigin("*")
public class GoalsAndObjectivesController {
    @Autowired
    private GoalsAndObjectivesService goalsAndObjectivesService;

    @PutMapping(value = "/save/goalsAndObjectives", produces = "application/json")
    @ApiOperation("Save or Update Goals And Objectives")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GoalsAndObjectivesDto> saveGoalsAndObjectives(@RequestBody GoalsAndObjectivesDto GoalsAndObjectivesDto) {
        GoalsAndObjectivesDto goalsAndObjectivesDto = goalsAndObjectivesService.saveGoalsAndObjectives(GoalsAndObjectivesDto);
        log.info("saveGoalsAndObjectives :" + goalsAndObjectivesDto);
        return new ResponseEntity<>(goalsAndObjectivesDto, HttpStatus.CREATED);
    }


    @GetMapping(value = "/read/goalsAndObjectives/{goalsAndObjectivesId}", produces = "application/json")
    @ApiOperation("Read Goals And Objectives")
    @ResponseStatus(HttpStatus.OK)
    public GoalsAndObjectivesDto readGoalsAndObjectives(@PathVariable("goalsAndObjectivesId") Long goalsAndObjectivesId) {
        log.info("ReadGoalsAndObjectives goalsAndObjectivesId :" + goalsAndObjectivesId);
        return goalsAndObjectivesService.readGoalsAndObjectives(goalsAndObjectivesId);
    }


    @DeleteMapping("/remove/goalsAndObjectives/{goalsAndObjectivesId}")
    @ApiOperation("Remove Goals And Objectives")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeGoalsAndObjectives(@PathVariable("goalsAndObjectivesId") Long goalsAndObjectivesId) {
        log.info("RemoveGoalsAndObjectives goalsAndObjectivesId :" + goalsAndObjectivesId);
        goalsAndObjectivesService.removeGoalsAndObjectives(goalsAndObjectivesId);
    }

    @GetMapping(value = "/readAll/goalsAndObjectives/{staffId}", produces = "application/json")
    @ApiOperation("Read All Goals And Objectives")
    public  ResponseEntity<List<GoalsAndObjectivesDto>> readAllGoalsAndObjectives(@PathVariable("staffId") Long staffId) {
        log.info("Read All GoalsAndObjectives staffId :" + staffId);
        return new ResponseEntity<List<GoalsAndObjectivesDto>>(goalsAndObjectivesService.readAllGoalsAndObjectives(staffId), HttpStatus.OK);

    }
}
