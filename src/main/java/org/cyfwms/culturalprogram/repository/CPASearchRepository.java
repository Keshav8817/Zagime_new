package org.cyfwms.culturalprogram.repository;
import org.cyfwms.culturalprogram.dto.CPASearchDto;
import org.cyfwms.culturalprogram.dto.CPASearchResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CPASearchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<CPASearchResultsDto> searchCulturalProgAndAct(CPASearchDto CPASearchDto) {
        List<Object> argsObjectList = new ArrayList<>();
        StringBuffer querySBuff = createSearchQuery(CPASearchDto, argsObjectList);
        return jdbcTemplate.query(querySBuff.toString(),argsObjectList.toArray(),
                (rs, rowNum) ->
                        new CPASearchResultsDto(
                                rs.getLong("CulturalProgramId"),
                                rs.getLong("referenceId"),
                                rs.getString("name"),
                                rs.getString("type"),
                                rs.getString("caseworker"),
                                rs.getDate("startDate")!=null?rs.getDate("startDate").toLocalDate(): LocalDate.of(1,1,1),
                        rs.getString("status")
                        )
        );
    }
    private StringBuffer createSearchQuery(CPASearchDto searchCriteria, List<Object> argsObjectList) {
        StringBuffer querySBuff = new StringBuffer();
        querySBuff.append("select c.culturalProgramId ,c.referenceid ,c.name ,c.type ,c.caseworker, c.startDate ,c.status ");
        querySBuff.append("from culturalprogandact c where c.statusofdeletion='ACTIVE'");

        Long referenceId = searchCriteria.getReferenceId();
        if (referenceId != null) {
            querySBuff.append(" AND c.referenceid = ?");
            argsObjectList.add(referenceId);
        }
        String name = searchCriteria.getName();
        if (name != null && !name.trim().isEmpty()) {
            name = name.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND c.name LIKE ?");
            argsObjectList.add(name + "%");
        }

        String type = searchCriteria.getType();
        if (type != null && !type.trim().isEmpty()) {
            type = type.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND c.type LIKE ?");
            argsObjectList.add(type + "%");
        }

        String caseworker = searchCriteria.getCaseworker();
        if (caseworker != null && !caseworker.trim().isEmpty()) {
            caseworker = caseworker.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND c.caseworker LIKE ?");
            argsObjectList.add(caseworker + "%");
        }

        LocalDate startDate = searchCriteria.getStartDate();
        if (startDate != null) {
            querySBuff.append(" AND c.startDate = ?");
            argsObjectList.add(startDate);
        }

        String status = searchCriteria.getStatus();
        if (status != null && !status.trim().isEmpty()) {
            status = status.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND c.status=?");
            argsObjectList.add(status);
        }
        return querySBuff;
    }
}
