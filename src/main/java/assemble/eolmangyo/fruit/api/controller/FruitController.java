package assemble.eolmangyo.fruit.api.controller;


import assemble.eolmangyo.fruit.api.response.FruitListResponseDto;
import assemble.eolmangyo.fruit.application.service.FruitServiceImpl;
import assemble.eolmangyo.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/fruit")
@RequiredArgsConstructor
public class FruitController {

	// service
	private final FruitServiceImpl fruitService;
	// util
	private final ModelMapper modelMapper;

	/**
	 * FruitController
	 * 1. 과일 리스트 조회
	 */

	// 1. 과일 리스트 조회
	@GetMapping("/list")
	public BaseResponse<FruitListResponseDto> getFruitList() {
		FruitListResponseDto responseDto = fruitService.getFruitList();
		return new BaseResponse<>(responseDto);
	}
}
