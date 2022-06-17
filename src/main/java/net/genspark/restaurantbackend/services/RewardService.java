package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.purchase.Purchase;
import net.genspark.restaurantbackend.entities.reward.Reward;
import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.repositories.RewardRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    private RewardRepository rewardRepository;
    private CustomUserDetailsService userDetailsService;

    public RewardService(RewardRepository rewardRepository, CustomUserDetailsService userDetailsService) {

        this.rewardRepository = rewardRepository;
        this.userDetailsService = userDetailsService;
    }

    public List<Reward> getRewards(String username) {

        User user = (User) userDetailsService.loadUserByUsername(username);

        return user.getRewards();
    }

    public List<Reward> getRewards() {

        return Streamable.of(rewardRepository.findAll()).toList();
    }

    public Reward getReward(int id) {

        return rewardRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    public boolean existsById(int id) {

        return rewardRepository.existsById(id);
    }

    public void addRewardsToUser(User user) {

        double totalPurchases = user.getPurchases().stream().map(Purchase::getPrice).reduce(0.0, (a, b) -> a + b);
        if (totalPurchases >= 150.00 && !user.getRewards().contains(getReward(3))) {
            user.addReward(getReward(3));
        } else if (totalPurchases >= 100.00 && !user.getRewards().contains(getReward(2))) {
            user.addReward(getReward(2));
        } else if (totalPurchases >= 50.00 && !user.getRewards().contains(getReward(1))) {
            user.addReward(getReward(1));
        }
    }
}
