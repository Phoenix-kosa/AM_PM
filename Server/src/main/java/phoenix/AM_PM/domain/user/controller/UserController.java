package phoenix.AM_PM.domain.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import phoenix.AM_PM.domain.user.dto.LoginRequestDto;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.global.config.jwt.JwtProperties;
import phoenix.AM_PM.global.config.service.JwtService;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtService jwtService;

  private JwtProperties jwtProperties;
//  @PostMapping("/user")
//  public b

  // 로그인
  @PostMapping("/api/auth/local")
  public ResponseEntity<String> login(@RequestBody LoginRequestDto loginDto,
      HttpServletResponse res) {
    User user = userRepository.findByUserId(loginDto.getUserId()).get();
    System.out.println("Login 성공 유저 정보" + user);
    System.out.println("로그인 rest api 진입");
    if (user != null) {
      String userId = user.getUserId();
      String token = jwtService.getToken("userId", userId);
      MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
      header.add(jwtProperties.HEADER_STRING, token);

      System.out.println("로그인 성공");

      return new ResponseEntity<>("로그인 성공", header, HttpStatus.OK);
      // authorization
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }


  // 로그아웃
  @DeleteMapping("/api/auth")
  public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String token, HttpServletResponse res) {
    System.out.println("로그아웃!!!!");
    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
    header.add("Authorization", "delete");
    return new ResponseEntity<>("로그아웃 성공", header, HttpStatus.OK);
  }
}
