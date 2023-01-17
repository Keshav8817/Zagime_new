package org.cyfwms.participant.repository;

import org.cyfwms.participant.dto.ParticipantAppointmentSearchCriteriaDto;
import org.cyfwms.participant.dto.ParticipantAppointmentSearchResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ParticipantAppointmentSearchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<ParticipantAppointmentSearchResultsDto> searchParticipantContactNotes(ParticipantAppointmentSearchCriteriaDto participantAppointmentSearchCriteriaDto) {
        List<Object> argsObjectList = new ArrayList<>();
        StringBuffer querySBuff = createSearchQuery(participantAppointmentSearchCriteriaDto, argsObjectList);
        return jdbcTemplate.query(querySBuff.toString(),argsObjectList.toArray(),
                (rs, rowNum) ->
                        new ParticipantAppointmentSearchResultsDto(
                                rs.getLong("appointmentid"),
                                rs.getLong("participantid"),
                                rs.getString("subject"),
                                rs.getString("status"),
                                rs.getDate("date")!=null?rs.getDate("date").toLocalDate(): LocalDate.of(1,1,1),
                                rs.getLong("participantappointmentid"),
                                rs.getTime("time")!=null?rs.getTime("time").toLocalTime(): LocalTime.of(1,1,1),

                                rs.getString("location"),
                                rs.getString("duration"),
                                rs.getString("client"),
                                rs.getString("caseworker"),
                                rs.getString("recurring_appointment"),
                                rs.getString("frequency"),
                                rs.getDate("end_date")!=null?rs.getDate("end_date").toLocalDate(): LocalDate.of(1,1,1),
                                rs.getString("notes")
                        )
        );
    }

    private StringBuffer createSearchQuery(ParticipantAppointmentSearchCriteriaDto searchCriteria, List<Object> argsObjectList) {
        StringBuffer  querySBuff = new StringBuffer();
        String data=searchCriteria.getData();
        Long participantId=searchCriteria.getParticipantId();

        querySBuff.append("select p.appointmentid ,p2.participantid,p2.participantappointmentid,p.subject ,p.status ,p.date,p.time,p.location,p.duration,p.client,p.caseworker,p.recurring_appointment,p.frequency,p.end_date,p.notes ");
        querySBuff.append("from appointments p left join participant_appointment p2 on p.appointmentid = p2.appointmentid where  p2.status='ACTIVE' ");

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

            querySBuff.append(" AND (p2.participantid=? OR p.subject LIKE ?  OR p.status LIKE ?  OR p.date LIKE ?  OR p.time LIKE ?  OR p.location LIKE ?  OR p.duration LIKE ?  OR p.client LIKE ?  OR p.caseworker LIKE ?  OR p.recurring_appointment LIKE ?  OR p.frequency LIKE ? OR p.end_date LIKE ? OR p.notes LIKE ? ) ");
            argsObjectList.add(data);
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");

        }
        else {
            querySBuff.append(" AND p2.participantid = ? ");
            argsObjectList.add(participantId);}

        return querySBuff;

    }
}
