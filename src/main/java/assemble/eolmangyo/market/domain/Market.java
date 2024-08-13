package assemble.eolmangyo.market.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Market {

	// 시장 id
	private Long id;

	// 시장 이름
	private String marketName;

	@Builder
	public Market(Long id, String marketName) {
		this.id = id;
		this.marketName = marketName;
	}

}
