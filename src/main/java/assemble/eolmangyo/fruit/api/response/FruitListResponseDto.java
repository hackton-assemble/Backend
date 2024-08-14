package assemble.eolmangyo.fruit.api.response;


import assemble.eolmangyo.fruit.domain.dto.FruitInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FruitListResponseDto {

	private List<FruitInfoDto> content;

}
