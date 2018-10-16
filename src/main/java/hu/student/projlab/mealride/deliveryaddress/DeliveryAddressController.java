package hu.student.projlab.mealride.deliveryaddress;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class DeliveryAddressController {

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("/addresses")
    //@ResponseBody
    public String getAddresses(Model model) {
        List<DeliveryAddress> addresses = deliveryAddressService.getUserAddresses();
        model.addAttribute("addresses", addresses);
        return "addresses";
    }

    @GetMapping("/address-modification")
    public String modifyAddress(Model model) {
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("address", address);
        return "address-modification";
    }

    @GetMapping("/addresses/{AddressId}")
    public String getAddress(Model model, @PathVariable Long AddressId) {
        return "address-modification";
    }

    @PostMapping("/addresses/{AddressId}")
    public String newAddress(Model model, @PathVariable Long AddressId) {
        return "address-modification";
    }

    @PutMapping("/addresses/{AddressId}")
    public String updateAddress(Model model, @PathVariable Long AddressId) {
        return "address-modification";
    }

    @DeleteMapping("/addresses/{AddressId}")
    public String deleteAddress(Model model, @PathVariable Long AddressId) {
        return "address-modification";
    }

}
