package phoenix.AM_PM.domain.user.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_id", nullable = false,unique = true)
  private String userId;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String nickname;

  @Column(name = "profile_img")
  private String profileImg;

  @Column(nullable = false,unique = true)
  private String email;

  @Column(nullable = false)
  private String roles;

  public List<String> getRoleList(){
    if(this.roles.length() > 0){
      return Arrays.asList(this.roles.split(","));
    }
    return new ArrayList<>();
  }

//  public static User createUser(String userId, String pw, PasswordEncoder passwordEncoder, String nickname, String profileImg, String email,String role) {
//    return new User(userId, passwordEncoder.encode(pw), nickname, profileImg, email, role);
//  }

}