package asc.portfolio.ascSb.web.dto.user;

import asc.portfolio.ascSb.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserQrAndNameResponseDto {

    private String name;
    private String qrCode;

    public UserQrAndNameResponseDto(UserQrAndNameResponseDto entity) {
        this.name = entity.getName();
        this.qrCode = entity.getQrCode();
    }
}