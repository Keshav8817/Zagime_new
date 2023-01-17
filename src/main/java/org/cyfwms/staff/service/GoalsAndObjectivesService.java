package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.GoalsAndObjectivesDto;

import java.util.List;

public interface GoalsAndObjectivesService {
    GoalsAndObjectivesDto saveGoalsAndObjectives(GoalsAndObjectivesDto goalsAndObjectivesDto);

    GoalsAndObjectivesDto readGoalsAndObjectives(Long goalsAndObjectivesId);

    void removeGoalsAndObjectives(Long goalsAndObjectivesId);

    List<GoalsAndObjectivesDto> readAllGoalsAndObjectives(Long staffId);
}
