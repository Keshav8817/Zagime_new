package org.cyfwms.staff.repository;

import org.cyfwms.staff.dto.StaffSearchCriteriaDto;
import org.cyfwms.staff.dto.StaffSearchResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffSearchRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StaffSearchResultsDto> searchStaffs(StaffSearchCriteriaDto staffSearchCriteriaDto) {
        List<Object> argsObjectList = new ArrayList<Object>();
        StringBuffer querySBuff = createSearchQuery(staffSearchCriteriaDto, argsObjectList);
        return jdbcTemplate.query(
                querySBuff.toString(), argsObjectList.toArray(),
                (rs, rowNum) ->
                        new StaffSearchResultsDto(
                                rs.getLong("staffId"),
                                rs.getString("firstName"),
                                rs.getString("middleName"),
                                rs.getString("lastName"),
                                rs.getDate("dateOfBirth") != null ?
                                        rs.getDate("dateOfBirth").toLocalDate() : LocalDate.of(1, 1, 1),
                                rs.getString("department"),
                                rs.getString("status"),
                                rs.getString("workLocation"),
                                rs.getString("supervisor"),
                                rs.getBoolean("active"),
                                rs.getString("employeeId")

                        )
        );
    }

    private StringBuffer createSearchQuery(StaffSearchCriteriaDto searchCriteria, List<Object> argsObjectList) {
        StringBuffer querySBuff = new StringBuffer();
        querySBuff.append("select s.staffid, s.firstname, s.middlename, s.lastname, s.dateofbirth, s.department, s.status, s.worklocation, s.supervisor,s2.active, s.employeeid ");
        querySBuff.append("from staff s left join staff_jobandbanking s2 on s.staffid = s2.staffid where s.statusofdeletion='ACTIVE'");

        String firstName = searchCriteria.getFirstName();
        if (firstName != null && !firstName.trim().isEmpty()) {
            firstName = firstName.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND s.firstname LIKE ?");
            argsObjectList.add(firstName + "%");
        }

        String middleName = searchCriteria.getMiddleName();
        if (middleName != null && !middleName.trim().isEmpty()) {
            middleName = middleName.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND s.middlename LIKE ?");
            argsObjectList.add(middleName + "%");
        }

        String surname = searchCriteria.getLastName();
        if (surname != null && !surname.trim().isEmpty()) {
            surname = surname.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND s.lastname LIKE ?");
            argsObjectList.add(surname + "%");
        }


        String workLocation = searchCriteria.getWorkLocation();
        if (workLocation != null && !workLocation.trim().isEmpty()) {
            workLocation = workLocation.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND s.worklocation=?");
            argsObjectList.add(workLocation);
        }
        String supervisor = searchCriteria.getSupervisor();
        if (supervisor != null && !supervisor.trim().isEmpty()) {
            supervisor = supervisor.trim()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            querySBuff.append(" AND s.supervisor LIKE ?");
            argsObjectList.add(supervisor + "%");
        }
        boolean active = searchCriteria.isActive();
        if (active){
            querySBuff.append(" AND s2.active = 1");
        }
        return querySBuff;

    }
}
