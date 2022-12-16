package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketForUserResponseDto;
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

@Repository
@RequiredArgsConstructor
@Slf4j
public class TicketCustomRepositoryImpl implements TicketCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<TicketForUserResponseDto> findAvailableTicketInfoByIdAndCafeName(Long id, String cafeName) {
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
    public Ticket findValidTicketInfoForAdminByUserIdAndCafeName(Long id, String cafeName) throws NullPointerException {
        LocalDateTime now = LocalDateTime.now();
        QTicket qTicket = new QTicket("qT");
        QTicket qTicketTwo = new QTicket("qF");

        try {
            Optional<Ticket> checkRemainTimeIsNotNull = Optional.ofNullable(query.select(qTicket)
                    .from(qTicket)
                    .where(qTicket.user.id.eq(id), qTicket.cafe.cafeName.eq(cafeName),
                            qTicket.remainingTime.isNotNull(),
                            qTicket.remainingTime.gt(0)
                    )
                    .fetchOne()); // 시간제 티켓이 있는지 확인
            Optional<Ticket> checkFixedTermIsNotNull = Optional.ofNullable(query.select(qTicketTwo)
                    .from(qTicketTwo)
                    .where(qTicketTwo.user.id.eq(id), qTicketTwo.cafe.cafeName.eq(cafeName),
                            qTicketTwo.fixedTermTicket.isNotNull(),
                            qTicketTwo.fixedTermTicket.gt(now)
                    )
                    .fetchOne()); // 기간제 티켓이 있는지 확인
            if(checkRemainTimeIsNotNull.isPresent()) {
                return checkRemainTimeIsNotNull.get(); // 시간제 티켓이 존재 => 시간제 티켓 return
            } else if (checkFixedTermIsNotNull.isPresent()) {
                return checkFixedTermIsNotNull.get(); // 기간제 티켓이 존재 = > 기간제 티켓 return
            } else {
                log.info("Valid 티켓이 존재하지 않습니다.");
                return null;
            }
        } catch(NullPointerException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Long verifyTicket() {
        LocalDateTime date = LocalDateTime.now();
        return query
                .update(ticket)
                .set(ticket.isValidTicket, TicketStateType.INVALID)
                .where(ticket.fixedTermTicket.lt(date), ticket.remainingTime.loe(0L))
                .execute();
    }

    @Override
    public List<TicketForUserResponseDto> findAllTicketInfoByLoginIdAndCafe(String loginId, Cafe cafe) {
        return query
                .select(Projections.bean(TicketForUserResponseDto.class,
                        ticket.isValidTicket, ticket.fixedTermTicket, ticket.partTimeTicket, ticket.remainingTime, ticket.productLabel))
                .from(ticket)
                .where(ticket.cafe.eq(cafe), ticket.user.loginId.eq(loginId))
                .orderBy(ticket.isValidTicket.desc(), ticket.createDate.asc())
                .fetch();
    }
}


