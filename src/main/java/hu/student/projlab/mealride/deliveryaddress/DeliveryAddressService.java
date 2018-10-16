package hu.student.projlab.mealride.deliveryaddress;


import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryAddressService {

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private UserService userService;

    public void addAddress(DeliveryAddress address, User user) {
        address.setUser(user);
        deliveryAddressRepository.save(address);
    }

    public List<DeliveryAddress> getAddresses() {
        List<DeliveryAddress> addresses = new ArrayList<>();
        deliveryAddressRepository.findAll().forEach(addresses::add);
        return addresses;
    }

    public List<DeliveryAddress> getUserAddresses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);
        return deliveryAddressRepository.findAllByUserId(user.getId());
    }

    public void updateAddress(DeliveryAddress address) {
        deliveryAddressRepository.save(address);
    }

    public void deleteAddress(DeliveryAddress address) {
        deliveryAddressRepository.delete(address);
    }


}
