package phoenix.AM_PM.domain.members.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMembers is a Querydsl query type for Members
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMembers extends EntityPathBase<Members> {

    private static final long serialVersionUID = 1924801648L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembers members = new QMembers("members");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final phoenix.AM_PM.domain.project.entity.QProject project;

    public final EnumPath<Roles> roles = createEnum("roles", Roles.class);

    public final phoenix.AM_PM.domain.user.entity.QUser user;

    public QMembers(String variable) {
        this(Members.class, forVariable(variable), INITS);
    }

    public QMembers(Path<? extends Members> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMembers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMembers(PathMetadata metadata, PathInits inits) {
        this(Members.class, metadata, inits);
    }

    public QMembers(Class<? extends Members> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new phoenix.AM_PM.domain.project.entity.QProject(forProperty("project")) : null;
        this.user = inits.isInitialized("user") ? new phoenix.AM_PM.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

