package asc.portfolio.ascSb.domain.ticket;

import asc.portfolio.ascSb.web.dto.ticket.TicketResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketCustomRepostioryImpl implements TicketCustomRepository {

    private final JPAQueryFactory query;

//    public List<TicketResponseDto> findTicketInfoById(Long id) {
//        return query
//                .select(ticket)
//    }

}
