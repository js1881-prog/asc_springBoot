package asc.portfolio.ascSb.domain.faq;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.faq.FAQResponseDto;

import java.util.ArrayList;
import java.util.List;

public interface FAQCustomRepository {

    List<FAQResponseDto> findAllFAQListGroupByCategory(User user);

}
