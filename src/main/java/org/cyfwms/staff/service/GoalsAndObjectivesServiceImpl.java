package org.cyfwms.staff.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.staff.dto.GoalsAndObjectivesDto;
import org.cyfwms.staff.entity.Staff;
import org.cyfwms.staff.entity.StaffGoalsAndObjectives;
import org.cyfwms.staff.repository.GoalsAndObjectivesRepository;
import org.cyfwms.staff.repository.StaffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class GoalsAndObjectivesServiceImpl implements GoalsAndObjectivesService {
    @Autowired
    private GoalsAndObjectivesRepository goalsAndObjectivesRepo;
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public GoalsAndObjectivesDto saveGoalsAndObjectives(GoalsAndObjectivesDto goalsAndObjectivesDto) {
        log.info("Inside saveGoalsAndObjectives");
        StaffGoalsAndObjectives goalsAndObjectives = null;
        if (goalsAndObjectivesDto.getGoalsAndObjectivesId() == 0) {
            goalsAndObjectives = new StaffGoalsAndObjectives();
            BeanUtils.copyProperties(goalsAndObjectivesDto, goalsAndObjectives);
            goalsAndObjectives.setStatusOfDeletion("ACTIVE");
        } else {
            goalsAndObjectives = goalsAndObjectivesRepo.findById(goalsAndObjectivesDto.getGoalsAndObjectivesId()).get();
            BeanUtils.copyProperties(goalsAndObjectivesDto, goalsAndObjectives);
        }
        goalsAndObjectives = goalsAndObjectivesRepo.save(goalsAndObjectives);
        goalsAndObjectivesDto.setStaffId(goalsAndObjectives.getStaffId());
        goalsAndObjectivesDto.setGoalsAndObjectivesId(goalsAndObjectives.getGoalsAndObjectivesId());
        log.info("Exit saveGoalsAndObjectives");
        return goalsAndObjectivesDto;
    }

    @Override
    public GoalsAndObjectivesDto readGoalsAndObjectives(Long goalsAndObjectivesId) {
        log.info("Inside ReadGoalsAndObjectives");
        GoalsAndObjectivesDto goalsAndObjectivesDto = new GoalsAndObjectivesDto();
        if (goalsAndObjectivesId!=0) {
            StaffGoalsAndObjectives goalsAndObjectives = goalsAndObjectivesRepo.findById(goalsAndObjectivesId).filter(active -> active.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                    new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(goalsAndObjectivesId))));
            BeanUtils.copyProperties(goalsAndObjectives, goalsAndObjectivesDto);
            if (goalsAndObjectives.getSupervisor()!=null) {
                if (!goalsAndObjectives.getSupervisor().equals("0")) {
                    Staff participant = staffRepository.findByStaffId(Long.parseLong(goalsAndObjectivesDto.getSupervisor()));
                    goalsAndObjectivesDto.setSupervisor(participant.getFirstName() + " " + participant.getLastName());
                    goalsAndObjectivesDto.setSupervisorId(participant.getStaffId());
                }
            }
            log.info("Exit ReadGoalsAndObjectives");
        }
        return goalsAndObjectivesDto;
    }

    @Override
    public void removeGoalsAndObjectives(Long goalsAndObjectivesId) {
        StaffGoalsAndObjectives goalsAndObjectives = goalsAndObjectivesRepo.findById(goalsAndObjectivesId).filter(active -> active.getStatusOfDeletion().equalsIgnoreCase("ACTIVE")).orElseThrow(() ->
                new NoSuchElementFoundException(messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(goalsAndObjectivesId))));

        goalsAndObjectives.setStatusOfDeletion("INACTIVE");
        goalsAndObjectivesRepo.save(goalsAndObjectives);
        log.info("Exit removeGoalsAndObjectives");
    }

    @Override
    public List<GoalsAndObjectivesDto> readAllGoalsAndObjectives(Long staffId) {
        log.info("Inside GetAllGoalsAndObjectives");
        List<GoalsAndObjectivesDto> goalsAndObjectivesDtoList = new ArrayList<GoalsAndObjectivesDto>();
        if (staffId != 0) {
            goalsAndObjectivesDtoList =
                    goalsAndObjectivesRepo.findByStaffId(staffId)
                            .stream()
                            .map(go -> {
                                GoalsAndObjectivesDto goDto = new GoalsAndObjectivesDto();
                                BeanUtils.copyProperties(go, goDto);
                                return goDto;
                            }).collect(Collectors.toList());
        }
        log.info("Exit GetAllGoalsAndObjectives");
        return goalsAndObjectivesDtoList;
    }
}
