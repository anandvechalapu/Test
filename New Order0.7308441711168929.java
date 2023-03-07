
Controller Class:

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewOrderController {

    private final NewOrderService newOrderService;

    public NewOrderController(NewOrderService newOrderService) {
        this.newOrderService = newOrderService;
    }

    @GetMapping("/newOrder")
    public void showNewOrderFunctionalities() {
        // code to show all functionalities over new order
    }

    @PostMapping("/searchAccount")
    public void searchByAccountNumber(@RequestParam String accountNumber) {
        // code to search by account number
    }

    @GetMapping("/buyerAction")
    public void showBuyerActionInfo() {
        // code to display info for buyer action
    }

    @PostMapping("/addOrder")
    public void addOrder() {
        // code to add order
    }

    @PostMapping("/addBuyer")
    public void addBuyer() {
        // code to add buyer
    }

    @PostMapping("/searchProduct")
    public void searchProduct(@RequestParam String productNameOrCategory) {
        // code to search product by name or category
    }

    @PostMapping("/addToShoppingCart")
    public void addToShoppingCart() {
        // code to add product to shopping cart
    }

    @GetMapping("/checkout")
    public void checkout() {
        // code to show checkout details
    }

    @PostMapping("/updateOrder")
    public void updateOrder() {
        // code to update order
    }

    @PostMapping("/cancelOrder")
    public void cancelOrder() {
        // code to cancel order
    }
}

Service Class:

import org.springframework.stereotype.Service;

@Service
public class NewOrderService {

    public void showNewOrderFunctionalities() {
        // code to show all functionalities over new order
    }

    public void searchByAccountNumber(String accountNumber) {
        // code to search by account number
    }

    public void showBuyerActionInfo() {
        // code to display info for buyer action
    }

    public void addOrder() {
        // code to add order
    }

    public void addBuyer() {
        // code to add buyer
    }

    public void searchProduct(String productNameOrCategory) {
        // code to search product by name or category
    }

    public void addToShoppingCart() {
        // code to add product to shopping cart
    }

    public void checkout() {
        // code to show checkout details
    }

    public void updateOrder() {
        // code to update order
    }

    public void cancelOrder() {
        // code to cancel order
    }
}

Repository Class:

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewOrderRepository extends JpaRepository {

}