package assemble.eolmangyo.user.domain;


import assemble.eolmangyo.fruit.domain.MarketFruit;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Stamp {

	// 스탬프 id
	private Long id;

	// 스탬프 번호
	private Integer stampNumber;

	// 유저
	private Users user;

	// 시장 과일 -> null 이면 시장 과일이 없는 스탬프
	private MarketFruit marketFruit;

	// 수령 유무
	private Boolean isReceived;

	@Builder
	public Stamp(Long id, Integer stampNumber, Users user, MarketFruit marketFruit, Boolean isReceived) {
		this.id = id;
		this.stampNumber = stampNumber;
		this.user = user;
		this.marketFruit = marketFruit;
		this.isReceived = isReceived;
	}

}
