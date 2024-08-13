package assemble.eolmangyo.global.config.application;


import assemble.eolmangyo.user.application.util.UuidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.UUID;


@Configuration
@RequiredArgsConstructor
public class AuthAppConfig {

	/**
	 * UUID Generator
	 * - Test UUID 생성자
	 * - Production UUID 생성자
	 */

	// Test UUID 생성자
	@Bean(name = "testUuidGenerator")
	@Profile("test")
	public UuidGenerator testUuidGenerator() {
		return () -> UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
	}


	// Production UUID 생성자
	@Bean
	@Profile("!test")
	public UuidGenerator uuidGenerator() {
		return UUID::randomUUID;
	}

}
