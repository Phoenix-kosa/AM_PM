package phoenix.AM_PM.domain.projectplan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectPlan is a Querydsl query type for ProjectPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectPlan extends EntityPathBase<ProjectPlan> {

    private static final long serialVersionUID = 1528663024L;

    public static final QProjectPlan projectPlan = new QProjectPlan("projectPlan");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> projectId = createNumber("projectId", Integer.class);

    public final StringPath sampleImg = createString("sampleImg");

    public final StringPath sampleUrl = createString("sampleUrl");

    public final StringPath title = createString("title");

    public QProjectPlan(String variable) {
        super(ProjectPlan.class, forVariable(variable));
    }

    public QProjectPlan(Path<? extends ProjectPlan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectPlan(PathMetadata metadata) {
        super(ProjectPlan.class, metadata);
    }

}

