package hu.student.projlab.mealride.deliveryaddress;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class DeliveryAddressController {

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("addresses")
    public String newAddress(Model model) {
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("address", address);
        return "addresses";
    }


}
