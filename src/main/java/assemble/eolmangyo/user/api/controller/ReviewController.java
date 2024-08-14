package assemble.eolmangyo.user.api.controller;


import assemble.eolmangyo.global.common.entity.CustomUserDetails;
import assemble.eolmangyo.global.common.response.BaseResponse;
import assemble.eolmangyo.user.api.facade.RegisterReviewFacade;
import assemble.eolmangyo.user.api.request.RegisterReviewRequestDto;
import assemble.eolmangyo.user.api.response.RegisterReviewResponseDto;
import assemble.eolmangyo.user.application.service.ReviewServiceImpl;
import assemble.eolmangyo.user.domain.dto.RegisterReviewOutDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/user/review")
@RequiredArgsConstructor
public class ReviewController {

	// service
	private final RegisterReviewFacade registerReviewFacade;
	// util
	private final ModelMapper modelMapper;

	/**
	 * ReviewController
	 * 1. 리뷰 등록
	 */

	// 1. 리뷰 등록
	@PostMapping("/register")
	@Operation(summary = "리뷰 등록", description = "리뷰를 등록하고 스탬프 정보를 받는다", tags = { "Review" })
	@SecurityRequirement(name = "Bearer Auth")
	public BaseResponse<RegisterReviewResponseDto> registerReview(
		@RequestBody RegisterReviewRequestDto requestDto,
		@AuthenticationPrincipal CustomUserDetails authentication) {
		RegisterReviewOutDto outDto = registerReviewFacade.registerReview(authentication.getUserUuid(), requestDto);
		RegisterReviewResponseDto responseDto = modelMapper.map(outDto, RegisterReviewResponseDto.class);
		return new BaseResponse<>(responseDto);
	}
}
