package assemble.eolmangyo.user.api.response;


import assemble.eolmangyo.fruit.domain.enums.FruitCountType;
import assemble.eolmangyo.user.domain.dto.ReviewInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListResponseDto {

	private String fruitName;
	private Long fruitId;
	private String marketName;
	private Long marketId;
	private FruitCountType countType;
	private List<ReviewInfoDto> reviews;
	private Long lastCursorId;

}
