package org.cyfwms.staff.repository;
import org.cyfwms.staff.entity.StaffGoalsAndObjectives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface GoalsAndObjectivesRepository extends JpaRepository<StaffGoalsAndObjectives,Long> {
    @Query(value = "select * from staff_goalsandobjectives where staffId=? AND statusofdeletion='ACTIVE'",nativeQuery = true)
    List<StaffGoalsAndObjectives> findByStaffId(Long staffId);
}
