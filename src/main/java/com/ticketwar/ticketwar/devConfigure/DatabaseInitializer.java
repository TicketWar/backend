package com.ticketwar.ticketwar.devConfigure;

import com.ticketwar.ticketwar.entity.*;
import com.ticketwar.ticketwar.repository.*;
import com.ticketwar.ticketwar.service.PerformanceService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;

@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final PerformanceRepository performanceRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final SeatRepository seatRepository;

    public DatabaseInitializer(PerformanceRepository performanceRepository, TicketRepository ticketRepository, UserRepository userRepository, OrderRepository orderRepository, SeatRepository seatRepository) {
        this.performanceRepository = performanceRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional // NOTE: 이부분 중요!
    public void run(String... args) throws Exception {

        Performance performance = performanceRepository.save(new Performance(
                "아이유 H.E.R",
                Year.of(2002).atMonth(6).atDay(18).atTime(20, 30).atZone(ZoneId.of("Asia/Seoul")), // 2002-06-18 20:30:00+09
                Year.of(2002).atMonth(6).atDay(18).atTime(20, 30).atZone(ZoneId.of("Asia/Seoul"))
        ));

        User user = userRepository.save(new User(
                "김민규",
                "kimminkyeu@gmail.com"
        ));

        Seat seat = seatRepository.save(new Seat(performance, "G8-14"));

        Ticket ticket = ticketRepository.save(new Ticket(seat, performance));
        Order order = orderRepository.save(new Order(user, LocalDateTime.now()));

        // NOTE: 아래 과정이 적용 되려면, @Transactional으로 함수를 묶어야 한다.... 이유를 알아내자.
        ticketRepository.findById(ticket.getId())
                        .map(ticket1 -> ticket1.getOrders().add(order));
    }
}
