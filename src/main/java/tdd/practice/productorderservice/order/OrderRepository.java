package tdd.practice.productorderservice.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    private Map<Long, Order> persistence = new HashMap<>();
//    private Long sequence = 0L;
//
//    public void save(Order order) {
//        order.assignId(++sequence);
//        persistence.put(order.getId(), order);
//    }
}
