package hu.student.projlab.mealride.deliveryaddress;


import hu.student.projlab.mealride.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryAddressService {

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    public void addAddress(DeliveryAddress address, User user) {
        address.setUser(user);
        deliveryAddressRepository.save(address);
    }

    public List<DeliveryAddress> getAddresses() {
        List<DeliveryAddress> addresses = new ArrayList<>();
        deliveryAddressRepository.findAll().forEach(addresses::add);
        return addresses;
    }

    public void updateAddress(DeliveryAddress address) {
        deliveryAddressRepository.save(address);
    }

    public void deleteAddress(DeliveryAddress address) {
        deliveryAddressRepository.delete(address);
    }


}
