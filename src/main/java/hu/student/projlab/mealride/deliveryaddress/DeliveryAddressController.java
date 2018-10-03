package hu.student.projlab.mealride.deliveryaddress;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
class DeliveryAddressController {

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    // how to get logged-in user id here?

    @GetMapping("/addresses")
    public String getAddresses(Model model) {
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("address", address);
        return "addresses";
    }

    @GetMapping("/addresses/{AddressId}")
    public String getAddress(Model model, @PathVariable Long AddressId) {
        return "addresses";
    }

    @PostMapping("/addresses/{AddressId}")
    public String newAddress(Model model, @PathVariable Long AddressId) {
        return "addresses";
    }

    @PutMapping("/addresses/{AddressId}")
    public String updateAddress(Model model, @PathVariable Long AddressId) {
        return "addresses";
    }

    @DeleteMapping("/addresses/{AddressId}")
    public String deleteAddress(Model model, @PathVariable Long AddressId) {
        return "addresses";
    }

}
