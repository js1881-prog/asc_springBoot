package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;
import asc.portfolio.ascSb.domain.cafe.Cafe;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static asc.portfolio.ascSb.domain.ticket.QTicket.ticket;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TicketCustomRepositoryImpl implements TicketCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<TicketResponseDto> findAvailableTicketInfoByIdAndCafeName(Long id, String cafeName) {
        Ticket findTicket = query
                .selectFrom(ticket)
                .where(ticket.cafe.cafeName.eq(cafeName), ticket.user.id.eq(id), ticket.isValidTicket.eq(TicketStateType.VALID))
                .fetchOne();

        if (findTicket == null) {
            return Optional.empty();
        }

        if (findTicket.isValidFixedTermTicket() || findTicket.isValidPartTimeTicket()) {
            return findTicket.toTicketResponseDto();
        } else {
            log.error("Throw IllegalStateException. Ticket={}", findTicket);
            throw new IllegalStateException();
        }
    }

    @Override
    public Optional<Ticket> findAvailableTicketByIdAndCafe(Long id, String cafeName) {
        Optional<Ticket> ticketOpt = Optional.ofNullable(query
                .selectFrom(ticket)
                .where(ticket.cafe.cafeName.eq(cafeName), ticket.user.id.eq(id), ticket.isValidTicket.eq(TicketStateType.VALID))
                .fetchOne());

        if (ticketOpt.isPresent()) {
            Ticket findTicket = ticketOpt.get();

            if (findTicket.isFixedTermTicket()) {
                if (!findTicket.isValidFixedTermTicket()) {
                    return Optional.empty();
                }
            } else {
                if (!findTicket.isValidPartTimeTicket()) {
                    return Optional.empty();
                }
            }
        }

        return ticketOpt;
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
                .orderBy(ticket.isValidTicket.desc(), ticket.createDate.asc())
                .fetch();
    }
}
