package phoenix.AM_PM.domain.user.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import phoenix.AM_PM.domain.user.dto.SaveUserDto;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository repository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  // user 저장/ 회원가입 service
  public boolean save(SaveUserDto user) {
    boolean result = true;

    User newUser = User.builder()
        .userId(user.getUserId())
        .password(bCryptPasswordEncoder.encode(user.getPassword()))
        .nickname(user.getNickname())
        .email(user.getEmail())
        .roles("USERS")
        .build();

    try {
      repository.save(newUser);
    } catch (Exception e) {
      System.out.println(e);
      result = false;
    }
    return result;
  }

  public List<User> findbynickname(String nickname) { return repository.findByNicknameContaining(nickname);}
  // userId 중복 체크
  public Optional<User> findbyUserId(String userId) {
    return repository.findByUserId(userId);
  }

  // email 중복 체크
  public Optional<User> findbyEmail(String email) {
    return repository.findByEmail(email);
  }
}
