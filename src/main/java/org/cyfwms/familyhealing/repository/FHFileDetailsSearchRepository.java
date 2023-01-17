package org.cyfwms.familyhealing.repository;

import org.cyfwms.familyhealing.dto.FHFileDetailsSearchCriteriaDto;
import org.cyfwms.familyhealing.dto.FHFileDetailsSearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FHFileDetailsSearchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<FHFileDetailsSearchResultDto> searchFileDetails(FHFileDetailsSearchCriteriaDto fileDetailsSearchCriteriaDto) {
        List<Object> argsObjectList = new ArrayList<Object>();
        StringBuffer querySBuff = createSearchQuery(fileDetailsSearchCriteriaDto, argsObjectList);
        return jdbcTemplate.query(
                querySBuff.toString(), argsObjectList.toArray(),
                (rs, rowNum) ->
                        new FHFileDetailsSearchResultDto(
                                rs.getLong("fhFileDetailsId"),
                                rs.getLong("fileNo"),
                                rs.getString("clientName"),
                                rs.getDate("startDate") != null ?
                                        rs.getDate("startDate").toLocalDate() : LocalDate.of(1, 1, 1),
                                rs.getDate("endDate") != null ?
                                        rs.getDate("endDate").toLocalDate() : LocalDate.of(1, 1, 1),
                                rs.getString("status"),
                                rs.getString("community"),
                                rs.getString("department"),
                                rs.getString("caseworker"),
                                rs.getDate("date") != null ?
                                        rs.getDate("date").toLocalDate() : LocalDate.of(1, 1, 1),
                                rs.getString("reason"),
                                rs.getString("notes")

                        )
        );
    }

    private StringBuffer createSearchQuery(FHFileDetailsSearchCriteriaDto searchCriteria, List<Object> argsObjectList) {
        StringBuffer querySBuff = new StringBuffer();
        querySBuff.append("select f.fhFileDetailsId, f.fileNo, CONCAT(firstname,' ', surname) AS clientName, f.startDate, f.endDate,  f.status, f.community,f.department, f.caseworker, f.date, f.reason, f.notes ");
        querySBuff.append("from fh_filedetails f  left join participant p on f.clientname = p.participantid where f.statusofdeletion='ACTIVE'");

        String clientName = searchCriteria.getClientName();
        if (clientName != null && !clientName.trim().isEmpty()) {
            clientName = clientName.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND (CONCAT(firstname,' ',surname) LIKE ? OR CONCAT(firstname,surname) LIKE ?)");
            argsObjectList.add(clientName + "%");
            argsObjectList.add(clientName + "%");
        }

        Long fileNo = searchCriteria.getFileNo();
        if (fileNo != null) {
            querySBuff.append(" AND f.fileno = ?");
            argsObjectList.add(fileNo);
        }


        String status = searchCriteria.getStatus();
        if (status != null && !status.trim().isEmpty()) {
            status = status.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND f.status LIKE ?");
            argsObjectList.add(status + "%");
        }

        String community = searchCriteria.getCommunity();
        if (community != null && !community.trim().isEmpty()) {
            community = community.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND f.community LIKE ?");
            argsObjectList.add(community + "%");
        }

        String caseworker = searchCriteria.getCaseworker();
        if (caseworker != null && !caseworker.trim().isEmpty()) {
            caseworker = caseworker.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND f.caseworker LIKE ?");
            argsObjectList.add(caseworker + "%");
        }

        LocalDate startDate = searchCriteria.getStartDate();
        if (startDate != null) {
            querySBuff.append(" AND f.startdate = ?");
            argsObjectList.add(startDate);
        }
        return querySBuff;
    }
}
