package org.cyfwms.staff.service;

import org.cyfwms.staff.dto.BackgroundCheckDto;

import java.util.List;

public interface BackgroundCheckService {
    BackgroundCheckDto saveBackgroundCheck(BackgroundCheckDto backgroundCheckDto);
    BackgroundCheckDto readBackgroundCheck(Long BackgrounCheckId);
    void deleteBackgroundCheck(Long BackgroundCheckId);
    List<BackgroundCheckDto> readAllBackgroundCheck(Long staffId);
}
