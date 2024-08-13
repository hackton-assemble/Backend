package assemble.eolmangyo.user.api.response;


import lombok.Getter;

import java.util.UUID;


@Getter
public class SignInResponseDto {

	private String accessToken;
	private UUID uuid;
	private String userNickname;

}
