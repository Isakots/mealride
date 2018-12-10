package hu.student.projlab.mealride.deliveryaddress;


import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryAddressService {

    private DeliveryAddressRepository deliveryAddressRepository;

    private UserService userService;

    @Autowired
    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository, UserService userService) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.userService = userService;
    }

    public void registerUserWithAddress(DeliveryAddress address, User user) {
        address.setUser(user);
        address.setCreated_at(System.currentTimeMillis());
        deliveryAddressRepository.save(address);
    }

    void addAddress(DeliveryAddress address) {
        address.setUser(userService.getCurrentUser());
        address.setCreated_at(System.currentTimeMillis());
        deliveryAddressRepository.save(address);
    }

    public List<DeliveryAddress> getAddresses() {
        List<DeliveryAddress> addresses = new ArrayList<>();
        deliveryAddressRepository.findAll().forEach(addresses::add);
        return addresses;
    }

    public List<DeliveryAddress> getUserAddresses() {
        User user = userService.getCurrentUser();
        return deliveryAddressRepository.findAllByUserId(user.getId());
    }

    public DeliveryAddress findById(Long id) {
       return deliveryAddressRepository.getDeliveryAddressById(id);
    }

    void deleteAddress(DeliveryAddress address) {
        address.setUser(null);
        address.setDeleted_at(System.currentTimeMillis());
        deliveryAddressRepository.save(address);
    }


}
