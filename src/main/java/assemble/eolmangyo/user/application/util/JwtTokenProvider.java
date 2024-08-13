package assemble.eolmangyo.user.application.util;


import org.springframework.security.core.userdetails.UserDetails;


public interface JwtTokenProvider<T> {

	/**
	 * TokenProvider
	 * 1. 액세스 토큰 생성
	 * 2. refresh 토큰 생성
	 * 3. 토큰에서 uuid 가져오기
	 */

	// 1. 액세스 토큰 생성
	String generateToken(UserDetails userDetails);

	// 2. refresh 토큰 생성
	String generateRefreshToken(UserDetails userDetails);

	// 3. 토큰에서 uuid 가져오기
	String validateAndGetUserUuid(String refreshToken);

}
