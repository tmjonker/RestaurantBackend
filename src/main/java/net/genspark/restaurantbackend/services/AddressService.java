package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {

        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {

        return addressRepository.save(address);
    }
}
