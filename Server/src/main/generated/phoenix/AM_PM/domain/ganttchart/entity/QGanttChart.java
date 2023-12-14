package phoenix.AM_PM.domain.ganttchart.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGanttChart is a Querydsl query type for GanttChart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGanttChart extends EntityPathBase<GanttChart> {

    private static final long serialVersionUID = -1722969762L;

    public static final QGanttChart ganttChart = new QGanttChart("ganttChart");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> projectId = createNumber("projectId", Integer.class);

    public QGanttChart(String variable) {
        super(GanttChart.class, forVariable(variable));
    }

    public QGanttChart(Path<? extends GanttChart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGanttChart(PathMetadata metadata) {
        super(GanttChart.class, metadata);
    }

}

