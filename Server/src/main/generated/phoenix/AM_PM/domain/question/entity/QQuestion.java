package phoenix.AM_PM.domain.question.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -1769183882L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final ListPath<phoenix.AM_PM.domain.answer.entity.Answer, phoenix.AM_PM.domain.answer.entity.QAnswer> comments = this.<phoenix.AM_PM.domain.answer.entity.Answer, phoenix.AM_PM.domain.answer.entity.QAnswer>createList("comments", phoenix.AM_PM.domain.answer.entity.Answer.class, phoenix.AM_PM.domain.answer.entity.QAnswer.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> projectId = createNumber("projectId", Integer.class);

    public final BooleanPath status = createBoolean("status");

    public final StringPath title = createString("title");

    public final phoenix.AM_PM.domain.user.entity.QUser user;

    public final StringPath userId = createString("userId");

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new phoenix.AM_PM.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

