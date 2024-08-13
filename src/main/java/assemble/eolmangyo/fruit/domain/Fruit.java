package assemble.eolmangyo.fruit.domain;


import assemble.eolmangyo.fruit.domain.enums.FruitCountType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fruit {

	// 과일 id
	private Long id;

	// 과일 이름
	private String fruitName;

	// 수량 타입
	private FruitCountType fruitCountType;

	@Builder
	public Fruit(Long id, String fruitName, FruitCountType fruitCountType) {
		this.id = id;
		this.fruitName = fruitName;
		this.fruitCountType = fruitCountType;
	}

}
