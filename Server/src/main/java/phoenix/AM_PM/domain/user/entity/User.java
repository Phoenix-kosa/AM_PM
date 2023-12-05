package phoenix.AM_PM.domain.user.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@Builder
public class User {

  @Id
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

}