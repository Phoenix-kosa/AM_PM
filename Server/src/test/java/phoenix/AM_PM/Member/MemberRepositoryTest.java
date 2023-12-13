package phoenix.AM_PM.Member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import phoenix.AM_PM.domain.member.entity.Member;
import phoenix.AM_PM.domain.member.entity.Roles;
import phoenix.AM_PM.domain.member.repository.MemberRepository;
import phoenix.AM_PM.domain.project.entity.Project;
import phoenix.AM_PM.domain.project.repository.ProjectRepository;
import phoenix.AM_PM.domain.user.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void save() {
        Member entity = new Member().builder()
                .user(userRepository.findById(1).get())
                .project(projectRepository.findById(1).get())
                .roles(Roles.member)
                .build();
        memberRepository.save(entity);

        memberRepository.findAll().stream().forEach(System.out::println);
    }
}
