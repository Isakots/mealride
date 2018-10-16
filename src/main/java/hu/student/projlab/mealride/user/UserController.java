package hu.student.projlab.mealride.user;


import hu.student.projlab.mealride.deliveryaddress.DeliveryAddress;
import hu.student.projlab.mealride.deliveryaddress.DeliveryAddressService;
import hu.student.projlab.mealride.exception.PasswordNotMatchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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


    @PostMapping("/registration")
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @ModelAttribute(value="user") User user,
                                   @ModelAttribute(value="address")DeliveryAddress address, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
            return modelAndView;
        }

        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
                        bindingResult.reject("email");
        } else {
            userService.addUser(user);
            deliveryAddressService.addAddress(address,user);
            modelAndView.addObject("successMessage", "You are registered successfully!");
        }

        modelAndView.setViewName("registration");
        return modelAndView;
    }

    // We will know which user is logged in, then we can autocomplete the form
    @GetMapping("/personaldata")
    public String getUserData(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "personaldata";
    }

    // We will know which user is logged in so we can call an updateUser method based on UserId
    @PostMapping("/personaldata")
    public String editUser(@ModelAttribute(value="user") User user) {
        userService.editUser(user);

        // redirecting to index.html to view it again..
        return "redirect:/personaldata";
    }

    @GetMapping("/password")
    public String changePasswordForm(Model model) {
        model.addAttribute("changer", new PasswordChanger());
        return "password";
    }

    @PostMapping("/password")
    public ModelAndView changePassword(ModelAndView modelAndView, @ModelAttribute(value="changer") PasswordChanger changer ) {

        try {
            userService.changePassword(changer);
            modelAndView.addObject("onPasswordChangeSuccess","Password is changed!");
        }
        catch(PasswordNotMatchingException e) {
            modelAndView.addObject("onPasswordChangeFailure",e.getMessage());
        }

        modelAndView.setViewName("password");
        return modelAndView;
    }


}
