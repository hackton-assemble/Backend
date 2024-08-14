package assemble.eolmangyo.market.api.response;


import assemble.eolmangyo.market.domain.dto.MarketInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketListResponseDto {

	List<MarketInfoDto> content;

}
