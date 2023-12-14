package phoenix.AM_PM.domain.ganttchart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.AM_PM.domain.ganttchart.entity.GanttCart;

public interface GanttchartRepository extends JpaRepository<GanttCart, String> {

}
