package assemble.eolmangyo.fruit.application.service;


import assemble.eolmangyo.fruit.api.response.FruitListResponseDto;
import assemble.eolmangyo.fruit.domain.dto.FruitInfoDto;
import assemble.eolmangyo.fruit.infrastructure.entity.FruitEntity;
import assemble.eolmangyo.fruit.infrastructure.jpa.FruitJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FruitServiceImpl {

	// repository
	private final FruitJpaRepository fruitJpaRepository;

	/**
	 * FruitServiceImpl
	 * 1. 과일 리스트 조회
	 */

	// 1. 과일 리스트 조회
	public FruitListResponseDto getFruitList() {
		List<FruitEntity> fruitList = fruitJpaRepository.findAll();
		List<FruitInfoDto> content = new ArrayList<>();
		fruitList.forEach(
			fruit -> content.add(new FruitInfoDto(fruit.getId(), fruit.getFruitName()))
		);
		return new FruitListResponseDto(content);
	}

}
