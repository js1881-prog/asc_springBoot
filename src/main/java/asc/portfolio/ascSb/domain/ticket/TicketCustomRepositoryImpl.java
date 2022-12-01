package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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
}
