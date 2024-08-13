package assemble.eolmangyo.fruit.domain.enums;


import assemble.eolmangyo.global.common.enums.BaseEnum;
import assemble.eolmangyo.global.common.enums.BaseEnumConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum FruitCountType implements BaseEnum<String, String> {

	/**
	 * 1. 코드 작성
	 * 2. field 선언
	 * 3. converter 구현
	 */

	// 1. 코드 작성
	COUNT("C", "개수"),
	AMOUNT("A", "무게");

	// 2. field 선언
	private final String code;
	private final String description;

	// 3. converter 구현
	@Converter(autoApply = true)
	static class thisConverter extends BaseEnumConverter<FruitCountType, String, String> {
		public thisConverter() {
			super(FruitCountType.class);
		}
	}



}
