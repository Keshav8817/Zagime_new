package org.cyfwms.initialcontact.repository;
import org.cyfwms.initialcontact.dto.ICClientSearchCriteriaDto;
import org.cyfwms.initialcontact.dto.ICClientSearchResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ICClientSearchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<ICClientSearchResultsDto> searchParticipant(ICClientSearchCriteriaDto initialContactClientSearchCriteriaDto) {
        List<Object> argsObjectList = new ArrayList<>();
        StringBuffer querySBuff = createSearchQuery(initialContactClientSearchCriteriaDto, argsObjectList);
        return jdbcTemplate.query(querySBuff.toString(),argsObjectList.toArray(),
                (rs, rowNum) ->
                        new ICClientSearchResultsDto(
                                rs.getLong("participantid"),
                                rs.getString("firstname"),
                                rs.getString("surname"),
                                rs.getLong("referenceid")
                        )
        );
    }
    private StringBuffer createSearchQuery(ICClientSearchCriteriaDto searchCriteria, List<Object> argsObjectList) {
        StringBuffer  querySBuff = new StringBuffer();
        String data=searchCriteria.getData();
        querySBuff.append("select p.participantid ,p.firstname ,p.surname, p.referenceid ");
        querySBuff.append("from participant p left join participantcontact p2 on p.participantid = p2.participantid where  p.status='ACTIVE'");
        if (data != null && !data.trim().isEmpty()) {
            data = data.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND (p.referenceid =? OR p.dateofbirth LIKE ? OR p.firstname LIKE ? OR p.surname LIKE ?  OR p.middlename LIKE ? OR p.maritalstatus LIKE ? OR p2.homephone LIKE ? OR p2.cellphone LIKE ? OR p2.workphone LIKE ? OR p2.city LIKE ?) ");
            argsObjectList.add(data);
            argsObjectList.add(data);
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
            argsObjectList.add(data);
            argsObjectList.add(data);
            argsObjectList.add(data);
            argsObjectList.add("%"+data +"%");
        }
        return querySBuff;
    }
}
