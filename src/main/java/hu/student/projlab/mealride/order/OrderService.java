package hu.student.projlab.mealride.order;


import hu.student.projlab.mealride.cart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
    }


}
