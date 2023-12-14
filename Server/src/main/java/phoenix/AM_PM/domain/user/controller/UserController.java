package phoenix.AM_PM.domain.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import phoenix.AM_PM.domain.user.dto.LoginRequestDto;
import phoenix.AM_PM.domain.user.dto.SaveUserDto;
import phoenix.AM_PM.domain.user.entity.User;
import phoenix.AM_PM.domain.user.repository.UserRepository;
import phoenix.AM_PM.domain.user.service.UserService;
import phoenix.AM_PM.global.config.jwt.JwtProperties;
import phoenix.AM_PM.global.config.service.JwtService;
import phoenix.AM_PM.global.config.service.JwtServiceImpl;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;
  private final UserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtServiceImpl jwtService;

  private JwtProperties jwtProperties;

  // 로그인
  @PostMapping("/api/auth/local")
  public ResponseEntity<String> login(@RequestBody LoginRequestDto loginDto,
      HttpServletResponse res) {
    return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
  }

  @GetMapping("/api/auth/google")
  public ResponseEntity<String> googlelogin(HttpServletResponse res) {
    System.out.println("google login");
    return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
  }

  // 회원 가입
  @PostMapping("/api/user")
  public ResponseEntity<String> join(@RequestBody SaveUserDto dto,  HttpServletResponse res) {
    if(userService.save(dto)) {
      return new ResponseEntity<>("성공적으로 회원 가입이 진행되었습니다.", HttpStatus.CREATED);
    }
    return new ResponseEntity<>("회원가입 오류", HttpStatus.BAD_REQUEST);

  }


  // 로그아웃
  @DeleteMapping("/api/auth")
  public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String token, HttpServletResponse res) {
    System.out.println("로그아웃!!!!");
    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
    header.add("Authorization", "delete");
    return new ResponseEntity<>("로그아웃 성공", header, HttpStatus.OK);
  }

  @GetMapping("/api/atoken")
  public ResponseEntity<String> test1(@RequestHeader(value = "Authorization", required = false) String token, HttpServletResponse res) {
    System.out.println("access token 인증");
    System.out.println("first check " + token);
    System.out.println("Get ID Test!! => " + jwtService.getId(token));
//    jwtService.getClaims(token);

    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
    header.add("Authorization", token);

    return new ResponseEntity<>("Test", header, HttpStatus.OK);
  }

  @GetMapping("/api/rtoken")
  public ResponseEntity<String> test2(@RequestHeader(value = "RefreshToken", required = false) String token, HttpServletResponse res) {
    System.out.println("토큰 재발급");
    System.out.println(jwtService.getId(token));
    System.out.println(jwtService.getClaims(token));
    return new ResponseEntity<>("토큰 재발급", HttpStatus.OK);
  }

  @GetMapping("/api/user/user_id/{userId}")
  public ResponseEntity<Boolean> checkUserId(@PathVariable("userId")String userId){
    Boolean body;
    try{
      userService.findbyUserId(userId).get();
      body = false;
    } catch (Exception e){
      body = true;
    }
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @GetMapping("/api/user/email/{email}")
  public ResponseEntity<Boolean> checkEmail(@PathVariable("email")String email){
    Boolean body;
    try{
      userService.findbyEmail(email);
      body = false;
    } catch (Exception e){
      body = true;
    }
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

}
