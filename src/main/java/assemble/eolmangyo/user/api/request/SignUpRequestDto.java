package assemble.eolmangyo.user.api.request;


import lombok.Getter;


@Getter
public class SignUpRequestDto {

	private String userName;
	private String loginId;
	private String loginPassword;
	private String userNickname;

}
