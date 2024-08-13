package assemble.eolmangyo.user.api.request;


import lombok.Getter;


@Getter
public class SignInRequestDto {

	private String loginId;
	private String loginPassword;

}
