package hu.student.projlab.mealride.deliveryaddress;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressSerive {

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;


}
