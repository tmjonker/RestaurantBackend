package net.genspark.restaurantbackend.commandlinerunners;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.services.MenuService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuCommandLineRunner implements CommandLineRunner {

    private MenuService menuService;

    public MenuCommandLineRunner(MenuService menuService) {

        this.menuService = menuService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<MenuItem> items = getMenuItems();

        for(MenuItem item : items) {
            if(!menuService.existsById(item.getId()))
                menuService.addMenuItem(item);
        }

    }

    private List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem(10, "Appetizer", "Onion Ring Tower", "Tower of Onion Rings",
                7.99, "https://media-cdn.tripadvisor.com/media/photo-s/07/4d/94/83/a-tower-of-onion-rings.jpg"));
        items.add(new MenuItem(11, "Appetizer", "Mozzarella Sticks", "Mozzarella sticks with marinara sauce",
                7.99, "https://static.onecms.io/wp-content/uploads/sites/43/2022/04/07/23596-fried-mozzarella-cheese-sticks-3x2-252.jpg"));
        items.add(new MenuItem(12, "Appetizer", "Boneless Wings", "Boneless wings with ranch or bleu cheese",
                8.99, "https://lilluna.com/wp-content/uploads/2018/05/honey-bbq-wings-resize-5.jpg"));
        items.add(new MenuItem(1, "Burger", "Double Cheeseburger", "Two beef patties with American cheese",
                3.99, "https://www.carlsjr.com/-/media/project/cke/carlsjr/products/charboiled-burgers/cj_site_705x742_doublecheeseburger.jpg"));
        items.add(new MenuItem(2, "Burger", "Cheeseburger", "One beef patty with American cheese",
                2.49, "https://www.hamburgerstand.com/wp-content/uploads/2015/03/burgers-cheeseburger-main.jpg"));
        items.add(new MenuItem(3, "Burger", "Triple Stack", "Three beef patties with American cheese",
                5.99, "https://images.fineartamerica.com/images-medium-large-5/large-beef-burger-with-cheese-and-salad-mark-sykes.jpg"));

        return items;
    }



}
