package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.persistence.*;
import java.util.*;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product("Laptop", "High-end gaming laptop", 1500.0, 10));
            productRepository.save(new Product("Smartphone", "Latest model smartphone", 700.0, 20));
            productRepository.save(new Product("Headphones", "Noise-cancelling headphones", 150.0, 50));
        };
    }
}

@Entity
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Product() {}

    public Product(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

@Entity
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    private double totalAmount;

    @ManyToOne
    private User user;

    public Order() {}

    public Order(User user, double totalAmount) {
        this.user = user;
        this.totalAmount = totalAmount;
        this.orderDate = new Date();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

interface ProductRepository extends JpaRepository<Product, Long> {}

interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}

@Service
class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

@Service
class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String password, String email) {
        User user = new User(username, password, email);
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

@Service
class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public Order createOrder(User user, double totalAmount) {
        Order order = new Order(user, totalAmount);
        return orderRepository.save(order);
    }
}

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/register", "/products").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
            .logout().permitAll();
        return http.build();
    }
}

@Controller
class EcommerceController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/products")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";  // thymeleaf template: products.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // thymeleaf template: login.html
    }

    @PostMapping("/login")
    public String processLogin(String username, String password) {
        User user = userService.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/products";
        }
        return "login";  // failed login
    }

    @GetMapping("/register")
    public String register() {
        return "register";  // thymeleaf template: register.html
    }

    @PostMapping("/register")
    public String processRegister(String username, String password, String email) {
        userService.registerUser(username, password, email);
        return "redirect:/login";
    }

    @GetMapping("/order/{userId}")
    public String createOrder(@PathVariable Long userId, Model model) {
        User user = userService.findUserByUsername(userId.toString());
        if (user != null) {
            double totalAmount = 500.0;  // Example amount
            orderService.createOrder(user, totalAmount);
            model.addAttribute("order", new Order(user, totalAmount));
            return "order";  // thymeleaf template: order.html
        }
        return "error";
    }
}

