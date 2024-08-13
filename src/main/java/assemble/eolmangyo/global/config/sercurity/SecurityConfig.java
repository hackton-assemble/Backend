package assemble.eolmangyo.global.config.sercurity;


import assemble.eolmangyo.global.common.exception.BaseExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	// REGULAR EXPRESSION
	private static final String REGEX_STRING = "[a-zA-Z]+";
	private static final String REGEX_INT = "\\d+";
	private static final String REGEX_UUID = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";

	// HTTP METHOD
	private static final String GET = HttpMethod.GET.name();
	private static final String POST = HttpMethod.POST.name();
	private static final String PUT = HttpMethod.PUT.name();
	private static final String PATCH = HttpMethod.PATCH.name();
	private static final String DELETE = HttpMethod.DELETE.name();

	// 허용할 url 배열
	private static final String[] commonUrl = new String[] {
		"/swagger-ui/**",       // 스웨거
		"/swagger-resources/**",// 스웨거
		"/v3/api-docs/**",      // 스웨거
		"/api-docs/**"          // 스웨거
	};

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authenticationProvider;
	private final BaseExceptionHandlerFilter baseExceptionHandlerFilter;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


	// securityFilterChain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			// Restful API를 사용하므로, csrf는 사용할 필요가 없다
			.csrf(CsrfConfigurer::disable)
			// 토큰 방식을 사용하므로, 서버에서 session을 관리하지 않음. 따라서 STATELESS로 설정
			.sessionManagement(
				sessionManagement -> sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			// FilterSecurityInterceptor에서 사용 -> 인증 절차에 대한 설정을 시작 : 필터를 적용시키지 않을 url과, 적용시킬 url을 구분
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				// 예비 요청을 허용
				.requestMatchers(org.springframework.web.cors.CorsUtils::isPreFlightRequest)
				.permitAll()
				// 로그인 없이 허용할 url
				.anyRequest().permitAll())
				// 이외의 url은 허용하지 않음
//				.anyRequest().authenticated())
			// 폼 로그인 사용 안함
			.formLogin(AbstractHttpConfigurer::disable)
			// authenticationProvider 설정 : 입력된 정보와 db의 정보를 비교하여, 인증에 성공하면 Authentication 객체를 생성하여 리턴해줌
			.authenticationProvider(authenticationProvider)
			// JWT 인증 필터를 UsernamePasswordAuthenticationFilter 전에 추가
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			// filter단에서 발생하는 에러를 처리할 ExceptionHandlerFilter를 OAuth2필터 앞에 추가한다
			.addFilterBefore(baseExceptionHandlerFilter, JwtAuthenticationFilter.class)
			// 인증, 인가에 실패한 경우
			.exceptionHandling(exceptionHandling -> exceptionHandling
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.accessDeniedHandler(customAccessDeniedHandler))
		;
		return http.build();
	}

}