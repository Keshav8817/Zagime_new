package org.cyfwms.culturalprogram.repository;
import org.cyfwms.culturalprogram.dto.CPASearchCriteriaDto;
import org.cyfwms.culturalprogram.dto.CPAParticipantSearchResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CPAParticipantSearchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<CPAParticipantSearchResultsDto> searchParticipantCulturalProgAndAct(CPASearchCriteriaDto CPASearchCriteriaDto) {
        List<Object> argsObjectList = new ArrayList<>();
        StringBuffer querySBuff = createSearchQuery(CPASearchCriteriaDto, argsObjectList);
        return jdbcTemplate.query(querySBuff.toString(),argsObjectList.toArray(),
                (rs, rowNum) ->  new CPAParticipantSearchResultsDto(
                                rs.getLong("participantculturalprogid"),
                                rs.getLong("culturalProgramId"),
                                rs.getString("fullName"),
                                rs.getString("role"),
                                rs.getString("notes")
                        )

        );
    }

    private StringBuffer createSearchQuery(CPASearchCriteriaDto searchCriteria, List<Object> argsObjectList) {
        StringBuffer  querySBuff = new StringBuffer();
        String data=searchCriteria.getData();
        Long culturalProgramId=searchCriteria.getCulturalProgramId();
        querySBuff.append("select p.participantculturalprogid ,p.culturalprogramid , CONCAT(firstname,' ', surname) AS fullName, p.role,p.notes ");
        querySBuff.append("from participantculturalprogram p  left join participant p2 on p.participantid = p2.participantid where  p.status='ACTIVE'");
        if (culturalProgramId != null) {
            querySBuff.append(" AND p.culturalprogramid = ?");
            argsObjectList.add(culturalProgramId);
        }
        if (data != null && !data.trim().isEmpty()) {
            data = data.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND (culturalprogramid=? OR p.notes LIKE ? OR p.role LIKE ? )ORDER BY p.creationdatetime desc ");
            argsObjectList.add(data);
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
        }
        return querySBuff;
    }
}
