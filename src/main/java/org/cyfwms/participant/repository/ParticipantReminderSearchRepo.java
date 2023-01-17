package org.cyfwms.participant.repository;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.ParticipantReminderSearchCriteriaDto;
import org.cyfwms.participant.dto.ParticipantReminderSearchResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ParticipantReminderSearchRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ParticipantReminderSearchResultsDto> searchParticipantReminder(ParticipantReminderSearchCriteriaDto participantReminderSearchCriteriaDto) {
       log.info("Inside searchParticipantReminder");
        List<Object> argsObjectList = new ArrayList<>();
        StringBuffer querySBuff = createSearchQuery(participantReminderSearchCriteriaDto, argsObjectList);
        return jdbcTemplate.query(querySBuff.toString(), argsObjectList.toArray(),
                (rs, rowNum) ->
                        new ParticipantReminderSearchResultsDto(
                                rs.getLong("reminderid"),
                                rs.getLong("participantid"),
                                rs.getString("assignedTo"),
                                rs.getString("regarding"),
                                rs.getString("subject"),
                                rs.getString("frequency"),
                                rs.getString("status"),
                                rs.getString("description"),

                                rs.getDate("reminderDate") != null ? rs.getDate("reminderDate").toLocalDate() : LocalDate.of(1, 1, 1),
                                rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : LocalDate.of(1, 1, 1),
                                rs.getLong("participantReminderId")
                        )
        );
    }

    private StringBuffer createSearchQuery(ParticipantReminderSearchCriteriaDto searchCriteriaDto, List<Object> argsObjectList) {
        StringBuffer querySBuff = new StringBuffer();
        String data = searchCriteriaDto.getData();
        Long participantId = searchCriteriaDto.getParticipantId();

        querySBuff.append("select p.reminderid ,p2.participantid ,p.assignedto ,p.regarding ,p.subject ,p.frequency ,p.status ,p.description ,p.reminderDate ,p.endDate ,p2.participantreminderid ");
        querySBuff.append("from reminder p left join participantreminder p2 on (p.reminderid = p2.reminderid) where p2.statusofdeletion='ACTIVE' ");

        if (participantId != null) {
            querySBuff.append(" AND p2.participantid = ?");
            argsObjectList.add(participantId);
        }


        if (data != null && !data.trim().isEmpty()) {
            data = data.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");

            querySBuff.append(" AND (p2.participantid=? OR p.assignedto LIKE ?  OR p.regarding LIKE ?  OR p.subject LIKE ?  OR p.frequency LIKE ?  OR p.status LIKE ? OR p.description LIKE ? OR p.reminderDate LIKE ? OR p.endDate LIKE ? ) ORDER BY p2.creationdatetime desc ");
            argsObjectList.add(data);
            argsObjectList.add("%" + data + "%");
            argsObjectList.add("%" + data + "%");
            argsObjectList.add("%" + data + "%");
            argsObjectList.add("%" + data + "%");
            argsObjectList.add("%" + data + "%");
            argsObjectList.add("%" + data + "%");
            argsObjectList.add("%" + data + "%");
            argsObjectList.add("%" + data + "%");


        } else {
            querySBuff.append(" AND p2.participantid = ? ORDER BY p2.creationdatetime desc ");
            argsObjectList.add(participantId);
        }
        return querySBuff;
    }
}
