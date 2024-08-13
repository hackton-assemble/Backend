package assemble.eolmangyo.user.domain;


import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

	// 유저 id
	private Long id;

	// 유저 이름
	private String userName;

	// 유저 UUID
	private UUID userUuid;

	// 로그인 아이디
	private String loginId;

	// 로그인 비밀번호
	private String loginPassword;

	// 유저 닉네임
	private String userNickname;

	@Builder
	public Users(Long id, String userName, UUID userUuid, String loginId, String loginPassword, String userNickname) {
		this.id = id;
		this.userName = userName;
		this.userUuid = userUuid;
		this.loginId = loginId;
		this.loginPassword = loginPassword;
		this.userNickname = userNickname;
	}

	/**
	 * User
	 */

}
