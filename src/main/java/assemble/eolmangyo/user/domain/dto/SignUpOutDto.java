package assemble.eolmangyo.user.domain.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
public class SignUpOutDto {

	private String accessToken;
	private UUID uuid;
	private String userNickname;

}
