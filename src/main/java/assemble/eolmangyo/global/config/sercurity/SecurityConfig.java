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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


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
	private static final String[] authUrl = new String[] {
		"/api/v1/user/auth/signup",         // 회원가입
		"/api/v1/user/auth/signin",         // 로그인
		"/api/v1/user/auth/signout",        // 로그아웃
		"/api/v1/user/auth/refresh",             // 토큰 재발급
		"/api/v1/user/auth/nickname-duplicate",  // 닉네임 중복 확인
		"/api/v1/user/auth/signup/social",       // 소셜 회원가입
		"/api/v1/user/auth/signin/social",       // 소셜 로그인
		"/api/v1/user/auth/social-link",         // 소셜 계정 연동
		"/api/v1/user/auth/email",               // 인증 이메일 전송
		"/api/v1/user/auth/verify/email",        // 이메일 인증 코드 확인
		"/api/v1/user/auth/email-duplicate",     // 이메일 중복 확인
		"/api/v1/user/auth/restore",            // 회원 복구
	};
	private static final String[] usersUrl = new String[] {
		"/api/v1/user/shorts-profile/{userUuid:" + REGEX_UUID + "}",   // 쇼츠의 유저 정보 조회
		"/api/v1/user/simple-profile/{userUuid:" + REGEX_UUID + "}",   // uuid로 유저 간단 정보 조회
		"/api/v1/user/password/reset",         // 유저 비밀번호 재설정
		"/api/v1/user/search",                      // 검색어로 유저 조회
	};
	private static final RequestMatcher[] shortsUrl = new RequestMatcher[] {
		new AntPathRequestMatcher("/api/v1/shorts/{userShortsId:" + REGEX_INT + "}", GET),      // 특정 shorts 조회
		new AntPathRequestMatcher("/api/v1/shorts/**/writer", GET), // shorts 작성자 조회
		new AntPathRequestMatcher("/api/v1/shorts/count/{userUuid:" + REGEX_UUID + "}", GET),      // 유저가 작성한 글 수 조회
	};
	private static final String[] feedUrl = new String[] {
		"/api/v1/shorts/main-feed/non-user/recommend",  // 비회원 추천 메인피드 조회
		"/api/v1/shorts/user-feed/{userUuid:" + REGEX_UUID + "}",          // 유저 피드 조회
		"/api/v1/shorts/search",                        // 게시글 검색
		"/api/v1/shorts/main-feed/random",                // 랜덤 메인피드 조회
	};
	private static final RequestMatcher[] musicUrl = new RequestMatcher[] {
		new AntPathRequestMatcher("/api/v1/shorts/music", GET),      // 음악 목록 조회
	};
	private static final RequestMatcher[] commentUrl = new RequestMatcher[] {
		new AntPathRequestMatcher("/api/v1/comment", GET),                  // 댓글 조회
		new AntPathRequestMatcher("/api/v1/comment/re-comment", GET),       // 대댓글 조회
		new AntPathRequestMatcher("/api/v1/comment/like/{commentDetailId:" + REGEX_INT + "}", GET),       // 댓글 좋아요 조회
		new AntPathRequestMatcher("/api/v1/comment/count/{userShortsId:" + REGEX_INT + "}", GET),         // 댓글 수 조회
		new AntPathRequestMatcher("/api/v1/comment/re-comment/count/{commentId:" + REGEX_INT + "}", GET)  // 대댓글 수 조회
	};
	private static final RequestMatcher[] interestUrl = new RequestMatcher[] {
		new AntPathRequestMatcher("/api/v1/interest", GET),        // 관심사 리스트 조회
	};
	private static final String[] petUrl = new String[] {
		"/api/v1/pet/category/main",    // 펫 메인카테고리 리스트 조회
	};
	private static final String[] followUrl = new String[] {
		"/api/v1/follow/{userUuid:" + REGEX_UUID + "}/following/count",    // 팔로잉 수 조회
		"/api/v1/follow/{userUuid:" + REGEX_UUID + "}/follower/count",     // 팔로워 수 조회
	};
	private static final String[] privacyUrl = new String[] {
		"/api/v1/user/privacy-setting/name-disclosure/{userUuid:" + REGEX_UUID + "}",    // 실명 공개 여부 조회
	};
	private static final String[] subscribeUrl = new String[] {
	};
	private static final String[] notificationUrl = new String[] {
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
				.requestMatchers(feedUrl).permitAll()
				.requestMatchers(musicUrl).permitAll()
				.requestMatchers(privacyUrl).permitAll()
				.requestMatchers(authUrl).permitAll()
				.requestMatchers(commonUrl).permitAll() // 공통 url
				.requestMatchers(usersUrl).permitAll()  // users 도메인
				.requestMatchers(shortsUrl).permitAll() // shorts 도메인
				.requestMatchers(commentUrl).permitAll() // comment 도메인
				.requestMatchers(interestUrl).permitAll() // interest 도메인
				.requestMatchers(petUrl).permitAll() // pet 도메인
				.requestMatchers(followUrl).permitAll() // follow 도메인
				.requestMatchers(subscribeUrl).permitAll() // subscribe 도메인
				.requestMatchers(notificationUrl).permitAll() // notification 도메인
				// 이외의 url은 허용하지 않음
				.anyRequest().authenticated())
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