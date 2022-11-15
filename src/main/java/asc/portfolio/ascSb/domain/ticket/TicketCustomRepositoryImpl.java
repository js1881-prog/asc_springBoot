package asc.portfolio.ascSb.domain.ticket;


import asc.portfolio.ascSb.domain.user.QUser;
import asc.portfolio.ascSb.web.dto.ticket.TicketSelectResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static asc.portfolio.ascSb.domain.ticket.QTicket.ticket;
import static asc.portfolio.ascSb.domain.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class TicketCustomRepositoryImpl implements TicketCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public List<TicketSelectResponseDto> findAvailableTicketInfoById(Long id) {

        QTicket ticket = new QTicket("ticket");
        QUser User = new QUser("user");

        return query
                .select(Projections.bean(TicketSelectResponseDto.class,
                ticket.isDeprecatedTicket, ticket.fixedTermTicket, ticket.partTimeTicket, ticket.remainingTime))
                .from(ticket)
                .leftJoin(user.tickets, ticket)
                .fetchJoin()
                .distinct()
                .fetch();

//                .select(Projections.bean(TicketSelectResponseDto.class,
//                ticket.isDeprecatedTicket, ticket.fixedTermTicket, ticket.partTimeTicket, ticket.remainingTime))
//                .from(ticket)
//                .leftJoin(user.tickets, ticket)
//                .fetchJoin()
//                .fetch();
    }

    @Override
    public Long verifyTicket() {
        LocalDateTime date = LocalDateTime.now();

        return query
                .update(ticket)
                .set(ticket.isDeprecatedTicket, "Y")
                .where(ticket.fixedTermTicket.lt(date))
                .execute();
    }
}
