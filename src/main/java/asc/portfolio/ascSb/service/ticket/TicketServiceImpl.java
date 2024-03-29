package asc.portfolio.ascSb.service.ticket;

import asc.portfolio.ascSb.domain.cafe.Cafe;
import asc.portfolio.ascSb.domain.order.Orders;
import asc.portfolio.ascSb.domain.product.ProductRepository;
import asc.portfolio.ascSb.domain.ticket.Ticket;
import asc.portfolio.ascSb.domain.ticket.TicketRepository;
import asc.portfolio.ascSb.domain.ticket.TicketStateType;
import asc.portfolio.ascSb.domain.user.User;
import asc.portfolio.ascSb.domain.user.UserRepository;
import asc.portfolio.ascSb.web.dto.bootpay.BootPayOrderDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketForAdminResponseDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketRequestDto;
import asc.portfolio.ascSb.web.dto.ticket.TicketForUserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService, TicketCustomService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public TicketForUserResponseDto userValidTicket(Long id, String cafeName) {
        LocalDateTime dateTime = LocalDateTime.now();
            Optional<TicketForUserResponseDto> optionalDto = ticketRepository.findAvailableTicketInfoByIdAndCafeName(id, cafeName);
            if(optionalDto.isPresent()) {
                TicketForUserResponseDto dto = optionalDto.get();

                if(dto.getFixedTermTicket() != null) {
                    long termData = Duration.between(dateTime, dto.getFixedTermTicket()).toMinutes();
                    dto.setPeriod(termData);
                }
                return dto;
            } else {
                log.info("보유중인 티켓이 존재하지 않습니다.");
                return null;
            }
        }

    @Override
    public Long saveProductToTicket(User user, BootPayOrderDto bootPayOrderDto, Orders orders) {
        Optional<TicketForUserResponseDto> findUserValidTicket =
                ticketRepository.findAvailableTicketInfoByIdAndCafeName(user.getId(), user.getCafe().getCafeName());

        if (findUserValidTicket.isPresent()) {
            log.info("이미 사용중인 티켓이 존재합니다"); // 사용중인 티켓에 시간(기간)추가
            String productLabel = findUserValidTicket.get().getProductLabel();
            Ticket ticket = ticketRepository.findByProductLabelContains(productLabel);

            if(bootPayOrderDto.getData().getName().contains("시간")) {
                if(ticket.getRemainingTime() == null) {
                    ticket.updateTicketRemainingTime(0L);
                }
                ticket.updateTicketRemainingTime(ticket.getRemainingTime() + distinguishPartTimeTicket(orders.getOrderProductName()));
            } else if (bootPayOrderDto.getData().getName().contains("일")) {
                if(ticket.getFixedTermTicket() == null) {
                    ticket.updateTicketFixedTermTicket(LocalDateTime.now());
                }
                ticket.updateTicketFixedTermTicket(distinguishUpdatedFixedTermTicket(orders.getOrderProductName(), ticket.getFixedTermTicket()));
            }

            Ticket saveData = ticketRepository.save(ticket);
            log.info("사용 중인 티켓 변경");
            return saveData.getId();
        }
        TicketRequestDto ticketDto = TicketRequestDto.builder()
                .user(user)
                .cafe(user.getCafe())
                .isValidTicket(TicketStateType.VALID)
                .ticketPrice(bootPayOrderDto.getData().getPrice())
                .productLabel(orders.getProductLabel())
                .build();
        System.out.println(bootPayOrderDto.getData().getName());
        if(bootPayOrderDto.getData().getName().contains("시간")) {
            ticketDto.setPartTimeTicket(distinguishPartTimeTicket(orders.getOrderProductName()));
            ticketDto.setRemainingTime(distinguishPartTimeTicket(orders.getOrderProductName()));
        } else if (bootPayOrderDto.getData().getName().contains("일")) {
            ticketDto.setFixedTermTicket(distinguishFixedTermTicket(orders.getOrderProductName()));
        }
        Ticket saveData = ticketRepository.save(ticketDto.toEntity());
        return saveData.getId();
    }

    @Override
    public List<TicketForUserResponseDto> lookupUserTickets(String targetUserLoginId, Cafe cafe) {
        return ticketRepository.findAllTicketInfoByLoginIdAndCafe(targetUserLoginId, cafe);
    }

    @Override
    public TicketForAdminResponseDto adminLookUpUserValidTicket(String userLoginId, String cafeName) throws NullPointerException {
        Optional<User> user = userRepository.findByLoginId(userLoginId);
        try {
            if (user.isPresent()) {
                User userDto = user.get();
                Long userDtoId = userDto.getId();
                Ticket ticket = ticketRepository.findValidTicketInfoForAdminByUserIdAndCafeName(userDtoId, cafeName);
                String productNameType = productRepository.findByProductLabelContains(ticket.getProductLabel()).getProductNameType().getValue();
                return new TicketForAdminResponseDto(ticket, productNameType);
            }
        } catch (NullPointerException exception) {
            log.info("유저의 유효한 티켓이 존재하지 않습니다.");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void setInvalidTicket(String productLabel) {
        Ticket deleteTicket = ticketRepository.findByProductLabelContains(productLabel);
        deleteTicket.changeTicketStateToInvalid();
        ticketRepository.save(deleteTicket);
    }

    @Override
    public Long updateAllValidTicketState() {
        return ticketRepository.updateAllValidTicketState();
    }

    @Override
    public List<Ticket> allInvalidTicketInfo() {
        return ticketRepository.findAllByIsValidTicketContains(TicketStateType.INVALID);
    }

    @Override
    public void deleteInvalidTicket(List<Ticket> tickets) {
       ticketRepository.deleteAll(tickets);
    }
}
