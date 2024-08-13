package assemble.eolmangyo.global.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class) // Auditing(자동 값 매핑)기능 추가
@Getter
@MappedSuperclass // 멤버 인스턴스가, entity의 컬럼으로 들어가도록 해줌
public abstract class BaseCreateTimeEntity {

	@CreatedDate // 최초 생성 시점
	@Column(updatable = false)
	private LocalDateTime createdAt;

}
