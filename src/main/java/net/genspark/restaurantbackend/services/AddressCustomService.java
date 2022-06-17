package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressCustomService {

    private AddressRepository addressRepository;

    public AddressCustomService(AddressRepository addressRepository) {

        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {

        return addressRepository.save(address);
    }
}
