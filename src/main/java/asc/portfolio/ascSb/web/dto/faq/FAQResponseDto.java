package asc.portfolio.ascSb.web.dto.faq;

import asc.portfolio.ascSb.domain.faq.FAQ;
import asc.portfolio.ascSb.domain.faq.FAQCategory;
import com.querydsl.core.Tuple;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FAQResponseDto {

    private FAQCategory category;

    private List<FAQSelectedDto> FAQ;

    public FAQResponseDto(FAQCategory category, List<FAQSelectedDto> fAQ) {
        this.category = category;
        this.FAQ = fAQ;
    }

}
