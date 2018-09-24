package hu.student.projlab.mealride.user;


import hu.student.projlab.mealride.deliveryaddress.DeliveryAddress;
import hu.student.projlab.mealride.deliveryaddress.DeliveryAddressService;
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
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("addresses", deliveryAddressService.getAddresses());
        return "users";
    }

    // but this is NOT rest..
    @PostMapping("/")
    public String addUser(@ModelAttribute(value="user") User user, @ModelAttribute(value="address")DeliveryAddress address) {
        userService.addUser(user);
        deliveryAddressService.addAddress(address,user);

        // redirecting to index.html to view it again..
        return "redirect:/";
    }


    // We will know which user is logged in, then we can autocomplete the form
    @GetMapping("/personaldata")
    public String getUserData(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "personaldata";
    }

    // We will know which user is logged in so we can call an updateUser method based on UserId
    @PostMapping("/personaldata")
    public String editUser(@ModelAttribute(value="user") User user) {
        userService.addUser(user);

        // redirecting to index.html to view it again..
        return "redirect:/personaldata";
    }


}
