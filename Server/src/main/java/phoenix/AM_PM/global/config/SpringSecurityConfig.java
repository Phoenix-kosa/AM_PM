package phoenix.AM_PM.global.config;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.security.authentication.AuthenticationManager;
		import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
		import org.springframework.security.config.annotation.web.builders.HttpSecurity;
		import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
		import org.springframework.security.config.http.SessionCreationPolicy;
		import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
		import org.springframework.security.web.SecurityFilterChain;
		import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
		import phoenix.AM_PM.domain.user.repository.UserRepository;
		import phoenix.AM_PM.global.config.jwt.JwtAuthenticationFilter;
		import phoenix.AM_PM.global.config.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
public class SpringSecurityConfig {

	@Autowired
	private UserRepository myUserRepository;

	@Autowired
	private CorsConfig corsConfig;

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.addFilter(corsConfig.corsFilter())
				.csrf((csrfConfig) ->
						csrfConfig.disable()
				)
				.sessionManagement((sessionManagement) ->
						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin((formLogin) -> formLogin.disable())
				.httpBasic((httpBasic) -> httpBasic.disable())
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilter(jwtAuthorizationFilter())
				.authorizeRequests()
//				.requestMatchers("/api/auth/local", "api/auth").permitAll()
				.requestMatchers("/**").permitAll()
				.anyRequest().authenticated();
		return http.build();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		System.out.println("등록");
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManagerBean());
		return jwtAuthenticationFilter;
	}

	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
		return new JwtAuthorizationFilter(authenticationManagerBean(), myUserRepository);
	}
}