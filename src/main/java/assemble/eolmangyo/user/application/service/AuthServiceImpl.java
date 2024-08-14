package assemble.eolmangyo.user.application.service;


import assemble.eolmangyo.global.common.entity.CustomUserDetails;
import assemble.eolmangyo.global.common.exception.BaseException;
import assemble.eolmangyo.global.common.response.BaseResponseStatus;
import assemble.eolmangyo.user.application.repository.UserRepository;
import assemble.eolmangyo.user.application.util.JwtTokenProvider;
import assemble.eolmangyo.user.application.util.UuidGenerator;
import assemble.eolmangyo.user.domain.Users;
import assemble.eolmangyo.user.domain.dto.LoginIdDuplicateCheckOutDto;
import assemble.eolmangyo.user.domain.dto.SignInOutDto;
import assemble.eolmangyo.user.domain.dto.SignUpInDto;
import assemble.eolmangyo.user.domain.dto.SignUpOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

	// repository
	private final UserRepository userRepository;
	// util
	private final UuidGenerator uuidGenerator;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;


	/**
	 * AuthService
	 * 1. 회원 가입
	 * 2. 로그인 아이디 중복 확인
	 * 3. 로그인
	 *
	 * private
	 * - 토큰 발급
	 */

	// 1. 회원 가입
	public SignUpOutDto signUp(SignUpInDto signUpInDto) {
		// 유저 생성
		String endcodedPassword = passwordEncoder.encode(signUpInDto.getLoginPassword());
		Users users = Users.builder()
			.userName(signUpInDto.getUserName())
			.userUuid(uuidGenerator.createUuid())
			.loginId(signUpInDto.getLoginId())
			.loginPassword(endcodedPassword)
			.userNickname(signUpInDto.getUserNickname())
			.build();
		// 유저 저장
		users = userRepository.save(users);
		// 토큰 발급
		String accessToken = createToken(users);
		return new SignUpOutDto(accessToken, users.getUserUuid(), users.getUserNickname());
	}


	// 2. 로그인 아이디 중복 확인
	public LoginIdDuplicateCheckOutDto loginIdDuplicateCheck(String loginId) {
		boolean isDuplicated = userRepository.existsByLoginId(loginId);
		return new LoginIdDuplicateCheckOutDto(isDuplicated);
	}


	// 3. 로그인
	public SignInOutDto signIn(String loginId, String loginPassword) {
		// 유저 조회
		Users users = userRepository.findByLoginId(loginId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
		// 유저 인증
		this.authenticate(users, loginPassword);
		// 토큰 발급
		String accessToken = createToken(users);
		return new SignInOutDto(accessToken, users.getUserUuid(), users.getUserNickname());
	}



	/**
	 * private
	 */

	// 토큰 발급
	private String createToken(Users users) {
		CustomUserDetails customUserDetails = new CustomUserDetails(users);
		return jwtTokenProvider.generateToken(customUserDetails);
	}

	// 유저 인증
	private void authenticate(Users users, String loginPassword) {
		CustomUserDetails customUserDetails = new CustomUserDetails(users);
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
			customUserDetails.getUserUuid(),
			loginPassword)
		);
	}




}
