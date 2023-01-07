package asc.portfolio.ascSb.service.faq;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.faq.FAQRequestDto;
import asc.portfolio.ascSb.web.dto.faq.FAQResponseDto;

import java.util.ArrayList;
import java.util.List;

public interface FAQService {

    List<FAQResponseDto> cafeFAQList(User user);

    Long saveFAQ(User user, FAQRequestDto faqRequestDto);
}
