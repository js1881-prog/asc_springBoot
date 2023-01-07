package asc.portfolio.ascSb.service.faq;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.faq.FAQRepository;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.faq.FAQRequestDto;
import asc.portfolio.ascSb.web.dto.faq.FAQResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;

    @Override
    public List<FAQResponseDto> cafeFAQList(User user) {
        try {
            List<FAQResponseDto> faqList = faqRepository.findAllFAQListGroupByCategory(user);
            return faqList;
        } catch (NullPointerException e) {
            log.info("FAQ가 존재하지 않습니다");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long saveFAQ(User user, FAQRequestDto faqRequestDto) {
        try {
            Long fAQId = faqRepository.save(faqRequestDto.toEntity(user)).getId();
            return fAQId;
        } catch (Exception e) {
            log.info("FAQ Save failed");
            e.printStackTrace();
            return null;
        }
    }
}
