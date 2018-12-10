package hu.student.projlab.mealride.order;


import hu.student.projlab.mealride.bankcard.BankCard;
import hu.student.projlab.mealride.bankcard.BankCardService;
import hu.student.projlab.mealride.cart.CartItem;
import hu.student.projlab.mealride.cart.ShoppingCart;
import hu.student.projlab.mealride.deliveryaddress.DeliveryAddress;
import hu.student.projlab.mealride.deliveryaddress.DeliveryAddressService;
import hu.student.projlab.mealride.exception.OrderException;
import hu.student.projlab.mealride.restaurant.Restaurant;
import hu.student.projlab.mealride.restaurant.RestaurantService;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource(name = "shoppingCart")
    private ShoppingCart shoppingCart;

    private OrderRepository orderRepository;

    private RestaurantService restaurantService;

    private UserService userService;

    private BankCardService bankCardService;

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantService restaurantService, UserService userService,
                        BankCardService bankCardService, DeliveryAddressService deliveryAddressService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.bankCardService = bankCardService;
        this.deliveryAddressService = deliveryAddressService;
    }

    User getUser() {
        return userService.getCurrentUser();
    }

    Restaurant getRestaurant(Long id) {
        return restaurantService.getRestaurantById(id);
    }

    List<CartItem> getCartItems() {
        return shoppingCart.getCartItems();
    }

    int getFullPrice() {
        return shoppingCart.getFullPrice();
    }

    List<BankCard> getCards() {
        return bankCardService.getBankcards();
    }

    List<DeliveryAddress> getAddresses() {
        return deliveryAddressService.getUserAddresses();
    }

    void createNewOrder(Long restId, Long addressId, Long cardNumber, String comment) throws OrderException {

        if(shoppingCart.getCartItems().isEmpty())
            throw new OrderException("Shopping Cart contains no meal!");

        if(!deliveryAddressService.getUserAddresses().contains(deliveryAddressService.findById(addressId)))
            throw new OrderException("This address is not in your saved addresses!");

        if(!bankCardService.getBankcards().contains(bankCardService.getCardByNumber(cardNumber)))
            throw new OrderException("Invalid Credit Card!");


        Order order = new Order( userService.getCurrentUser(), restaurantService.getRestaurantById(restId),
                shoppingCart.getCartItems(), deliveryAddressService.findById(addressId),
                bankCardService.getCardByNumber(cardNumber),shoppingCart.getFullPrice(),
                System.currentTimeMillis(),null,comment,null);

        orderRepository.save(order);

        shoppingCart.getCartItems().clear();
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }
    public List<Order> getRestaurantOrders(Long restId) {
        return orderRepository.findByRestaurant_Id(restId);
    }
}
