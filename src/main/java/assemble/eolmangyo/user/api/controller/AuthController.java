package assemble.eolmangyo.user.api.controller;


import assemble.eolmangyo.global.common.response.BaseResponse;
import assemble.eolmangyo.user.api.request.SignUpRequestDto;
import assemble.eolmangyo.user.api.response.SignUpResponseDto;
import assemble.eolmangyo.user.application.service.AuthServiceImpl;
import assemble.eolmangyo.user.domain.dto.SignUpInDto;
import assemble.eolmangyo.user.domain.dto.SignUpOutDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/user/auth")
@RequiredArgsConstructor
public class AuthController {

	// service
	private final AuthServiceImpl authService;
	// util
	private final ModelMapper modelMapper;


	/**
	 * AuthController
	 * 1. 회원가입
	 * 2. 아이디 중복 확인
	 * 3. 로그인
	 */

	// 1. 회원가입
	@PostMapping("/signup")
	@Operation(summary = "회원가입", description = "회원가입", tags = { "User Sign" })
	public BaseResponse<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
		SignUpInDto signUpInDto = modelMapper.map(signUpRequestDto, SignUpInDto.class);
		SignUpOutDto signUpOutDto = authService.signUp(signUpInDto);
		SignUpResponseDto signUpResponseDto = modelMapper.map(signUpOutDto, SignUpResponseDto.class);
		return new BaseResponse<>(signUpResponseDto);
	}




	// health check
	@GetMapping("health-check")
	public String healthCheck() {
		return "health-check. OK";
	}

}
