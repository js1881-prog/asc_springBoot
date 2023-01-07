package asc.portfolio.ascSb.domain.faq;

import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.web.dto.faq.FAQResponseDto;
import asc.portfolio.ascSb.web.dto.faq.FAQSelectedDto;
import asc.portfolio.ascSb.web.dto.seat.SeatSelectResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static asc.portfolio.ascSb.domain.faq.QFAQ.fAQ;
import static asc.portfolio.ascSb.domain.seat.QSeat.seat;

@RequiredArgsConstructor
@Repository
public class FAQCustomRepositoryImpl implements FAQCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public List<FAQResponseDto> findAllFAQListGroupByCategory(User user) throws NullPointerException {
        List<FAQResponseDto> faqResponseDtos = new ArrayList<>();

        for (FAQCategory faqCategory: FAQCategory.values()) {
            List<FAQSelectedDto> fAQList =
                    query
                    .select(Projections.bean(FAQSelectedDto.class, fAQ.question, fAQ.answer))
                    .from(fAQ)
                    .where(fAQ.fAQcategory.eq(faqCategory), fAQ.cafe.eq(user.getCafe()))
                    .orderBy(fAQ.id.desc())
                    .fetch();

            if(!fAQList.isEmpty()) {
                faqResponseDtos.add(new FAQResponseDto(faqCategory, fAQList));
            }
        }
        return faqResponseDtos;
    }
}

