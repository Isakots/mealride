package hu.student.projlab.mealride.deliveryaddress;


import hu.student.projlab.mealride.restaurant.RestaurantAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping("/addresses")
    //@ResponseBody
    public String getAddresses(Model model) {
        List<DeliveryAddress> addresses = deliveryAddressService.getUserAddresses();
        model.addAttribute("thisaddress", new DeliveryAddress());
        model.addAttribute("addresses", addresses);
        return "user/addresses";
    }

    @GetMapping("/address-modification")
    public String modifyAddress(Model model) {
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("address", address);
        return "user/address-modification";
    }

    @GetMapping("/newaddress")
    public String newAddress(Model model) {
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("address", address);
        return "user/address-modification";
    }

    //@ModelAttribute(value = "address")
    //public DeliveryAddress newRestAdmin() {return new DeliveryAddress();}

    @PostMapping("/addresses/modify")
    public String preFillModifyAddressForm(@RequestParam(value="addressId") Long addressId, Model model) {
        model.addAttribute("address",deliveryAddressService.findById(addressId));
        return "user/address-modification";
    }

    @PostMapping("/addresses/delete")
    public ModelAndView deleteAddress(@RequestParam(value="addressId") Long addressId) {

        DeliveryAddress deliveryAddress = deliveryAddressService.findById(addressId);
        deliveryAddressService.deleteAddress(deliveryAddress);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("address",deliveryAddressService.findById(addressId));
        modelAndView.setViewName("redirect:/addresses");
        return modelAndView;
    }

    // Here we have to handle modify and new address as well
    @PostMapping("/address-modification")
    public ModelAndView processAddressForm(@ModelAttribute DeliveryAddress address) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("addresses"));
        deliveryAddressService.addAddress(address);
        modelAndView.addObject("address",deliveryAddressService.getAddresses());
        return modelAndView;
    }

 }
