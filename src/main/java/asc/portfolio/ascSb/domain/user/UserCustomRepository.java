package asc.portfolio.ascSb.domain.user;

import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;

import java.util.List;

public interface UserCustomRepository {

    // TODO findAllById 구현.
    List<UserQrAndNameResponseDto> findQrAndUserNameById(Long id);



}
