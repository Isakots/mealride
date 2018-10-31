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

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private UserService userService;

    public void registerUserWithAddress(DeliveryAddress address, User user) {
        address.setUser(user);
        deliveryAddressRepository.save(address);
    }

    public void addAddress(DeliveryAddress address) {
        address.setUser(userService.getCurrentUser());
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

    public DeliveryAddress getAddresByStreet(String street) {
        return deliveryAddressRepository.findByStreet(street);
    }

    public Optional<DeliveryAddress> findById(Long id) {
       return deliveryAddressRepository.findById(id);
    }

    public void updateAddress(DeliveryAddress address) {
        deliveryAddressRepository.save(address);
    }

    public void deleteAddress(DeliveryAddress address) {
        deliveryAddressRepository.delete(address);
    }


}
