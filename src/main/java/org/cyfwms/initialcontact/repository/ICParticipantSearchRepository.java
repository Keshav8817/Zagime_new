package org.cyfwms.initialcontact.repository;
import org.cyfwms.initialcontact.dto.ICParticipantSearchCriteriaDto;
import org.cyfwms.initialcontact.dto.ICParticipantSearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ICParticipantSearchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ICParticipantSearchResultDto> searchICParticipant(ICParticipantSearchCriteriaDto iCParticipantSearchCriteriaDto) {
        List<Object> argsObjectList = new ArrayList<>();
        StringBuffer querySBuff = createSearchQuery(iCParticipantSearchCriteriaDto, argsObjectList);
        return jdbcTemplate.query(querySBuff.toString(),argsObjectList.toArray(),
                (rs, rowNum) ->  new ICParticipantSearchResultDto(
                        rs.getLong("icparticipantid"),
                        rs.getLong("filedetailsid"),
                        rs.getString("fullName"),
                        rs.getString("role"),
                        rs.getString("notes")
                )

        );
    }

    private StringBuffer createSearchQuery(ICParticipantSearchCriteriaDto iCParticipantSearchCriteriaDto, List<Object> argsObjectList) {
        StringBuffer  querySBuff = new StringBuffer();
        String data=iCParticipantSearchCriteriaDto.getData();
        Long fileDetailsId=iCParticipantSearchCriteriaDto.getFileDetailsId();
        querySBuff.append("select p.icparticipantid ,p.filedetailsid , CONCAT(firstname,' ', surname) AS fullName, p.role,p.notes ");
        querySBuff.append("from icparticipant p  left join participant p2 on p.participantid = p2.participantid where  p.status='ACTIVE'");
        if (fileDetailsId != null) {
            querySBuff.append(" AND p.filedetailsid = ?");
            argsObjectList.add(fileDetailsId);
        }
        if (data != null && !data.trim().isEmpty()) {
            data = data.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND (filedetailsid=? OR p.notes LIKE ? OR p.role LIKE ? )ORDER BY p.creationdatetime desc ");
            argsObjectList.add(data);
            argsObjectList.add("%" +data + "%");
            argsObjectList.add("%" +data + "%");
        }
        else {
            querySBuff.append(" AND p.filedetailsid = ?  ORDER BY p.creationdatetime desc ");
            argsObjectList.add(fileDetailsId);}
        return querySBuff;
    }
}
