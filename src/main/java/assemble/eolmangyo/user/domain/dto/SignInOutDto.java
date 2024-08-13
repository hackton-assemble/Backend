package assemble.eolmangyo.user.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInOutDto {

	private String accessToken;
	private UUID uuid;
	private String userNickname;

}
