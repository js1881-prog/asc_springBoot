package asc.portfolio.ascSb.web.dto.faq;

import asc.portfolio.ascSb.domain.faq.FAQ;
import asc.portfolio.ascSb.domain.faq.FAQCategory;
import asc.portfolio.ascSb.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FAQRequestDto {

    private FAQCategory category;
    private String question;
    private String answer;

    public FAQ toEntity(User user) {
        return FAQ.builder()
                .cafe(user.getCafe())
                .user(user)
                .fAQCategory(category)
                .question(question)
                .answer(answer)
                .build();
    }
}
