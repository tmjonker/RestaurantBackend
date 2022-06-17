package net.genspark.restaurantbackend.repositories;

import net.genspark.restaurantbackend.entities.address.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
