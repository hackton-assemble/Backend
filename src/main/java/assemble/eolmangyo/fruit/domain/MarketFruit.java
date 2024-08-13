package assemble.eolmangyo.fruit.domain;


import assemble.eolmangyo.market.domain.Market;
import assemble.eolmangyo.market.domain.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MarketFruit {

	// 시장 과일 id
	private Long id;

	// 시장
	private Market market;

	// 가게
	private Store store;

	// 과일
	private Fruit fruit;

	@Builder
	public MarketFruit(Long id, Market market, Store store, Fruit fruit) {
		this.id = id;
		this.market = market;
		this.store = store;
		this.fruit = fruit;
	}

}
