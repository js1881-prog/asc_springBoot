package asc.portfolio.ascSb.domain.user;

import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;

public interface UserCustomRepository {

    // TODO findAllById 구현.
    UserQrAndNameResponseDto findQrAndUserNameById(Long id);


}
