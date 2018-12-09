package hu.student.projlab.mealride.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value="SELECT * FROM orders o INNER JOIN customer_orders co ON o.order_id = co.order_id INNER JOIN " +
            "user u ON co.customer_id = u.user_id WHERE u.user_id = :userid ", nativeQuery =  true)
    List<Order> findByUser_Id(@Param("userid")Long userId);

    @Query(value="SELECT * FROM orders o INNER JOIN restaurant_orders ro ON o.order_id = ro.order_id INNER JOIN " +
            "restaurant r ON ro.restaurant_id = r.id WHERE r.id = :restid ", nativeQuery =  true)
    List<Order> findByRestaurant_Id(@Param("restid")Long restId);
}
