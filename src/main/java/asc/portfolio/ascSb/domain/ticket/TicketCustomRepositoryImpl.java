package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketForUserResponseDto;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static asc.portfolio.ascSb.domain.ticket.QTicket.ticket;

@Repository
@RequiredArgsConstructor
public class TicketCustomRepositoryImpl implements TicketCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<TicketForUserResponseDto> findAvailableTicketInfoByIdAndCafeName(Long id, String cafeName) {
        return Optional.ofNullable(query
                .select(Projections.bean(TicketForUserResponseDto.class,
                        ticket.isValidTicket, ticket.fixedTermTicket, ticket.partTimeTicket, ticket.remainingTime))
                .from(ticket)
                .where(ticket.cafe.cafeName.eq(cafeName), ticket.user.id.eq(id), ticket.isValidTicket.eq(TicketStateType.VALID))
                .fetchOne());
    }

    @Override
    public Ticket findValidTicketInfoForAdminByUserIdAndCafeName(Long id, String cafeName) {
        LocalDateTime now = LocalDateTime.now();
        QTicket qTicket = new QTicket("subT");
        return query.select(qTicket)
                .from(qTicket)
                .where(qTicket.user.id.eq(id), qTicket.cafe.cafeName.eq(cafeName),
                        qTicket.remainingTime.gt(0), qTicket.fixedTermTicket.gt(now)
                )
                .fetchOne();
    }

    @Override
    public Long verifyTicket(Long userId, Long cafeId) {
        LocalDateTime date = LocalDateTime.now();
        return query
                .update(ticket)
                .set(ticket.isValidTicket, TicketStateType.INVALID)
                .where(ticket.fixedTermTicket.lt(date))
                .execute();
    }

    @Override
    public List<TicketForUserResponseDto> findAllTicketInfoByLoginIdAndCafe(String loginId, Cafe cafe) {
        return query
                .select(Projections.bean(TicketForUserResponseDto.class,
                        ticket.isValidTicket, ticket.fixedTermTicket, ticket.partTimeTicket, ticket.remainingTime))
                .from(ticket)
                .where(ticket.cafe.eq(cafe), ticket.user.loginId.eq(loginId))
                .orderBy(ticket.isValidTicket.desc(), ticket.createDate.asc())
                .fetch();
    }
}
