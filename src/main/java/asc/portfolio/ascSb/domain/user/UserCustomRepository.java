package asc.portfolio.ascSb.domain.user;

import asc.portfolio.ascSb.web.dto.user.UserQrAndNameResponseDto;

public interface UserCustomRepository {

    UserQrAndNameResponseDto findQrAndUserNameById(Long id);
}
