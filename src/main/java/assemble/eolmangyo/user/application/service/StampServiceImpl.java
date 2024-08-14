package assemble.eolmangyo.user.application.service;


import assemble.eolmangyo.user.application.repository.StampRepository;
import assemble.eolmangyo.user.domain.Stamp;
import assemble.eolmangyo.user.domain.Users;
import assemble.eolmangyo.user.domain.dto.RegisterStampInDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class StampServiceImpl {

	// repository
	private final StampRepository stampRepository;
	// util
	private final ModelMapper modelMapper;

	/**
	 * StampService
	 * 1. 스탬프 등록
	 * 2. 유저의 마지막 스탬프 조회
	 */

	// 1. 스탬프 등록
	public Stamp registerStamp(RegisterStampInDto inDto) {
		Stamp stamp = modelMapper.map(inDto, Stamp.class);
		// 스탬프 저장
		return stampRepository.save(stamp);
	}

	// 2. 유저의 마지막 스탬프 조회
	public Stamp findLastStamp(Users user) {
		return stampRepository.findLastStamp(user)
			.orElseThrow(() -> new IllegalArgumentException("해당 유저의 마지막 스탬프가 존재하지 않습니다. user=" + user));
	}

}
