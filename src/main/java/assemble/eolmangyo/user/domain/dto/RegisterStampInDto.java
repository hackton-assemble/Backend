package assemble.eolmangyo.user.domain.dto;


import assemble.eolmangyo.fruit.domain.MarketFruit;
import assemble.eolmangyo.user.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStampInDto {

	// 스탬프 번호
	private Integer stampNumber;

	// 유저
	private Users user;

	// 시장 과일 -> null 이면 시장 과일이 없는 스탬프
	private MarketFruit marketFruit;

	// 수령 유무
	private Boolean isReceived;

}
