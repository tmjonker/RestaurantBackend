package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.entities.purchase.Purchase;
import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.purchaserequest.PurchaseRequest;
import net.genspark.restaurantbackend.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    PurchaseRepository purchaseRepository;
    CustomUserDetailsService userDetailsService;
    RewardService rewardService;
    MenuService menuService;

    AddressCustomService addressService;

    public PurchaseService(PurchaseRepository purchaseRepository, CustomUserDetailsService userDetailsService,
                           RewardService rewardService, MenuService menuService, AddressCustomService addressService) {

        this.purchaseRepository = purchaseRepository;
        this.userDetailsService = userDetailsService;
        this.rewardService = rewardService;
        this.menuService = menuService;
        this.addressService = addressService;
    }

    public Purchase addPurchase(PurchaseRequest purchaseRequest) {

        User user = (User) userDetailsService.loadUserByUsername(purchaseRequest.getUsername());

        List<MenuItem> menuItems = new ArrayList<>();
        purchaseRequest.getMenuIds().forEach(item -> menuItems.add(menuService.getMenuItemById(item)));

        Address address = addressService.saveAddress(purchaseRequest.getAddress());

        Purchase purchase = new Purchase(purchaseRequest.getDate(), purchaseRequest.getPrice(), address);
        menuItems.forEach(item -> purchase.addMenuItem(item));

        user.addPurchase(purchase);
        user.addAddress(address);

        rewardService.addRewardsToUser(user);

        purchaseRepository.save(purchase);

        userDetailsService.saveUser(user);

        return purchase;
    }
}
