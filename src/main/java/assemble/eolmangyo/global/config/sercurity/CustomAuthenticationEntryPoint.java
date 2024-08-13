package assemble.eolmangyo.global.config.sercurity;


import assemble.eolmangyo.global.common.exception.BaseException;
import assemble.eolmangyo.global.common.response.BaseResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// 인증 되어있지 않은 경우를 다룬다
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
		log.error("로그인 필요");
		throw new BaseException(BaseResponseStatus.NO_SIGN_IN);
	}

}
