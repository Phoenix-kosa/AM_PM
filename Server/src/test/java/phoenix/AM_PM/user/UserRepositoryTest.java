package phoenix.AM_PM.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  UserRepository userR;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  @Transactional
//  @Rollback(value = false)
  public void save(){

    try {
      userR.save(User.builder()
          .userId("maeng")
          .password(passwordEncoder.encode("1234"))
          .email("mmm@nae.com")
          .profileImg("")
          .nickname("맹")
          .roles("USERS")
          .build());
      userR.save(User.builder()
          .userId("pinix")
          .password(passwordEncoder.encode("1111"))
          .email("phoenix@nae.com")
          .profileImg("")
          .nickname("불사조")
          .roles("USERS")
          .build());
    } catch (Exception e){
      System.out.println(e);
    }
    userR.findAll().stream().forEach(System.out::println);
  }

  @Test
  public void findUserId() {
    User user = userR.findByUserId("maeng").get();
    System.out.println("Test Find userId" + user);
  }

  @Test
  public void findEmail() {
    User user = userR.findByEmail("mmm@nae.com").get();
    System.out.println("Test Find Email" + user);
  }


}

