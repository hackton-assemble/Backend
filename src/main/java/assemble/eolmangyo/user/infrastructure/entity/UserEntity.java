package assemble.eolmangyo.user.infrastructure.entity;


import assemble.eolmangyo.global.common.entity.BaseTimeEntity;
import assemble.eolmangyo.user.domain.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_uuid", nullable = false)
	private UUID userUuid;

	@Column(name = "user_name", length = 50, nullable = false)
	private String userName;

	@Column(name = "login_id", length = 50, nullable = false)
	private String loginId;

	@Column(name = "login_password", length = 100, nullable = false)
	private String loginPassword;

	@Column(name = "nick_name", length = 20, nullable = false)
	private String nickName;

	/**
	 * UserEntity
	 * 1. fromDomain
	 * 2. toDomain
	 */

	// 1. fromDomain
	public static UserEntity fromDomain(Users users) {
		return UserEntity.builder()
			.userUuid(users.getUserUuid())
			.userName(users.getUserName())
			.loginId(users.getLoginId())
			.loginPassword(users.getLoginPassword())
			.nickName(users.getUserNickname())
			.build();
	}

	// 2. toDomain
	public Users toDomain() {
		return Users.builder()
			.id(id)
			.userUuid(userUuid)
			.userName(userName)
			.loginId(loginId)
			.loginPassword(loginPassword)
			.userNickname(nickName)
			.build();
	}

}
