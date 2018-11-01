package hu.student.projlab.mealride.deliveryaddress;


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
        model.addAttribute("addresses", addresses);
        return "user/addresses";
    }

    @GetMapping("/address-modification")
    public String modifyAddress(Model model) {
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("address", address);
        return "user/address-modification";
    }

    @PostMapping("/addresses/{addressId}")
    public ModelAndView preFillModifyAddressForm(@PathVariable Long addressId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("address",deliveryAddressService.findById(addressId));
        modelAndView.setViewName("user/address-modification");
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
