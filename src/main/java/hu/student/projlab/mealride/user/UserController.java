package hu.student.projlab.mealride.user;


import hu.student.projlab.mealride.deliveryaddress.DeliveryAddress;
import hu.student.projlab.mealride.deliveryaddress.DeliveryAddressSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class UserController {

    @Autowired
     private UserService userService;

    @Autowired
    private DeliveryAddressSerive deliveryAddressService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("addresses", deliveryAddressService.getAddresses());
        return "users";
    }

    @GetMapping("/personaldata")
    public String getUserData(Model model, @ModelAttribute(value="user") User user) {
        return "personaldata";
    }

    // but this is NOT rest..
    @PostMapping("/")
    public String addUser(@ModelAttribute(value="user") User user, @ModelAttribute(value="address")DeliveryAddress address) {
        userService.addUser(user);
        deliveryAddressService.addAddress(address,user);

        // redirecting to index.html to view it again..
        return "redirect:/";
    }

}
