package phoenix.AM_PM.domain.chat.repository.querydsl.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import phoenix.AM_PM.domain.chat.entity.Chat;
import phoenix.AM_PM.domain.chat.entity.QChat;
import phoenix.AM_PM.domain.chat.repository.querydsl.ChatRepositoryCustom;
import static phoenix.AM_PM.domain.chat.entity.QChat.chat;

import java.util.List;

@Repository
public class ChatRepositoryCustomImpl extends QuerydslRepositorySupport implements ChatRepositoryCustom {
    public ChatRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory){
        super(Chat.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<Chat> chatPage(Pageable pageable, Integer projectId, Integer cursorId) {
        QChat chat = QChat.chat;

        List<Chat> result = jpaQueryFactory
                .selectFrom(chat)
                .where(ltCursorId(cursorId),  //마지막으로 조회 된 게시글의 번호
                    checkProjectId(projectId))
                .orderBy(chat.createdDate.desc()) //최신 순
                .limit(pageable.getPageSize() + 1) //가져올 데이터의 개수
                .fetch();

        Slice<Chat> chats = checkLastPage(pageable, result);

        return chats;
    }
    private BooleanExpression checkProjectId(Integer projectId)
    {
        return projectId != null ? chat.project.id.eq(projectId) : null;
    }
    //첫번째 페이지를 조회하기 위해 사용되는 쿼리
    private BooleanExpression ltCursorId(Integer cursorId)
    {
        //lt = cursorId 보다 작은지 검사
        return cursorId >= 0 ? chat.id.lt(cursorId) : null;
    }

    private Slice<Chat> checkLastPage(Pageable pageable, List<Chat> chats)
    {
        boolean hasNext = false; //다음으로 가져올 데이터가 있는 지 여부를 알려줌
        if(chats.size() > pageable.getPageSize())
        {
            hasNext = true; //읽어 올 데이터가 있다면 true를 반환
            chats.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(chats, pageable, hasNext);
    }
}
