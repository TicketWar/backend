package com.ticketwar.ticketwar.configure;

import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.repository.CustomerRepository;
import com.ticketwar.ticketwar.order.entity.Order;
import com.ticketwar.ticketwar.order.repository.OrderRepository;
import com.ticketwar.ticketwar.performance.entity.Performance;
import com.ticketwar.ticketwar.performance.repository.PerformanceRepository;
import com.ticketwar.ticketwar.seat.entity.Seat;
import com.ticketwar.ticketwar.seat.entity.SeatStatus;
import com.ticketwar.ticketwar.seat.repository.SeatRepository;
import com.ticketwar.ticketwar.ticket.entity.Ticket;
import com.ticketwar.ticketwar.ticket.repository.TicketRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner {
  private final PerformanceRepository performanceRepository;
  private final TicketRepository ticketRepository;
  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;
  private final SeatRepository seatRepository;

  public DatabaseInitializer(
      PerformanceRepository performanceRepository,
      TicketRepository ticketRepository,
      CustomerRepository customerRepository,
      OrderRepository orderRepository,
      SeatRepository seatRepository) {
    this.performanceRepository = performanceRepository;
    this.ticketRepository = ticketRepository;
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
    this.seatRepository = seatRepository;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    Performance performance =
        performanceRepository.save(
            Performance.builder()
                .name("아이유 H.E.R")
                .ticketingStartAt(
                    Year.of(2002)
                        .atMonth(6)
                        .atDay(18)
                        .atTime(20, 30)
                        .atZone(ZoneId.of("Asia/Seoul")))
                .ticketingEndAt(
                    Year.of(2002)
                        .atMonth(6)
                        .atDay(18)
                        .atTime(20, 30)
                        .atZone(ZoneId.of("Asia/Seoul")))
                .build());

    Customer customer =
        customerRepository.save(
            Customer.builder().nickname("김민규").email("kimminkyeu@gmail.com").build());

    Seat seat =
        seatRepository.save(
            Seat.builder()
                .performance(performance)
                .position("G8-14")
                .seatStatus(SeatStatus.AVAILABLE)
                .build());

    Ticket ticket =
        ticketRepository.save(Ticket.builder().performance(performance).seat(seat).build());

    Order order =
        orderRepository.save(
            Order.builder().customer(customer).orderedAt(LocalDateTime.now()).build());

    ticketRepository.findById(ticket.getId()).map(ticket1 -> ticket1.getOrders().add(order));
  }
}
