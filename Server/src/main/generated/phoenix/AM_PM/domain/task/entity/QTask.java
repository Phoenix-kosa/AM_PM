package phoenix.AM_PM.domain.task.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTask extends EntityPathBase<Task> {

    private static final long serialVersionUID = 156063924L;

    public static final QTask task = new QTask("task");

    public final StringPath backgroundColor = createString("backgroundColor");

    public final DateTimePath<java.time.LocalDateTime> beginDate = createDateTime("beginDate", java.time.LocalDateTime.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final StringPath frontColor = createString("frontColor");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> projectId = createNumber("projectId", Integer.class);

    public final StringPath userId = createString("userId");

    public QTask(String variable) {
        super(Task.class, forVariable(variable));
    }

    public QTask(Path<? extends Task> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTask(PathMetadata metadata) {
        super(Task.class, metadata);
    }

}

