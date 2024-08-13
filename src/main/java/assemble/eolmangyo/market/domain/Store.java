package assemble.eolmangyo.market.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

	// 가게 id
	private Long id;

	// 시장
	private Market market;

	// 가게 이름
	private String storeName;

	// 가게 위치
	private String storeLocation;

	@Builder
	public Store(Long id, Market market, String storeName, String storeLocation) {
		this.id = id;
		this.market = market;
		this.storeName = storeName;
		this.storeLocation = storeLocation;
	}

}
