package hu.student.projlab.mealride.order;


import hu.student.projlab.mealride.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
//@RequestMapping(value="/order")
class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /*@GetMapping("/order")
    public ModelAndView showOrderForm(/*@RequestParam(value="restId") Long restId, ModelAndView modelAndView, final BindingResult result) {

        if (result.hasErrors()) {
            modelAndView.addObject("error", "There are some error!");
            return modelAndView;
        }

        //modelAndView.addObject("rest", orderService.getRestaurant(restId));
        modelAndView.addObject("user", orderService.getUser());
        modelAndView.addObject("cartitems", orderService.getCartItems());
        modelAndView.addObject("fullprice", orderService.getFullPrice());
        modelAndView.addObject("addresses", orderService.getAddresses());
        modelAndView.addObject("cards", orderService.getCards());
        modelAndView.setViewName("user/order");

        return modelAndView;

    }*/


    @PostMapping("/order")
    public ModelAndView OrderForm(@RequestParam(value="restId") Long restId, ModelAndView modelAndView, final BindingResult result) {

        modelAndView = new ModelAndView(new RedirectView("/order"));

        if (result.hasErrors()) {
            modelAndView.addObject("error", "There are some error!");
            return modelAndView;
        }

        modelAndView.addObject("rest", orderService.getRestaurant(restId));
        modelAndView.addObject("user", orderService.getUser());
        modelAndView.addObject("cartitems", orderService.getCartItems());
        modelAndView.addObject("fullprice", orderService.getFullPrice());
        modelAndView.addObject("addresses", orderService.getAddresses());
        modelAndView.addObject("cards", orderService.getCards());
        modelAndView.setViewName("user/order");
        return modelAndView;

    }





}
