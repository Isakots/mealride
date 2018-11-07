package hu.student.projlab.mealride.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/order")
class OrderController {

    @Autowired
    private OrderService orderService;



}
