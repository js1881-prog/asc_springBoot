package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static asc.portfolio.ascSb.domain.ticket.QTicket.ticket;

@Repository
@RequiredArgsConstructor
public class TicketCustomRepositoryImpl implements TicketCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public TicketResponseDto findAvailableTicketInfoById(Long id, String cafeName) {

        return query
                .select(Projections.bean(TicketResponseDto.class,
                ticket.isValidTicket, ticket.fixedTermTicket, ticket.partTimeTicket, ticket.remainingTime))
                .from(ticket)
                .where(ticket.cafe.cafeName.eq(cafeName), ticket.user.id.eq(id), ticket.isValidTicket.eq(TicketStateType.VALID))
                .fetchOne();
    }

    @Override
    public Long verifyTicket() {
        LocalDateTime date = LocalDateTime.now();

        return query
                .update(ticket)
                .set(ticket.isValidTicket, TicketStateType.INVALID)
                .where(ticket.fixedTermTicket.lt(date))
                .execute();
    }

    @Override
    public List<TicketResponseDto> findAllTicketInfoByLoginIdAndCafe(String loginId, Cafe cafe) {
        return query
                .select(Projections.bean(TicketResponseDto.class,
                        ticket.isValidTicket, ticket.fixedTermTicket, ticket.partTimeTicket, ticket.remainingTime))
                .from(ticket)
                .where(ticket.cafe.eq(cafe), ticket.user.loginId.eq(loginId))
                .orderBy(ticket.isValidTicket.asc(), ticket.createDate.desc())
                .fetch();
    }
}
