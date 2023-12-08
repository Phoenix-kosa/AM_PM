
package phoenix.AM_PM.domain.projectplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.projectplan.entity.ProjectPlan;

import java.util.List;

public interface ProjectPlanRepository extends JpaRepository<ProjectPlan, Integer> {

    int countByProjectIdAndTitleStartingWith(int projectId, String titleStartsWith);

    List<ProjectPlan> findByProjectIdAndTitleStartingWith(int projectId, String titleStartsWith);
    List<ProjectPlan> findByProjectId(int projectId);


}

