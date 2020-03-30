package ru.itis.coffeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.coffeeservice.models.*;
import ru.itis.coffeeservice.repositories.*;

import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class CoffeeserviceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CoffeeserviceApplication.class, args);

        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        DiscountRepository discountRepository = context.getBean(DiscountRepository.class);
        LoginRepository loginRepository = context.getBean(LoginRepository.class);
        OrderRepository orderRepository = context.getBean(OrderRepository.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        SupplierRepository supplierRepository = context.getBean(SupplierRepository.class);

        /* CATEGORIES */
        Category coffee = Category.builder()
                .name("Coffee to go")
                .description("Caffeine-containing drinks")
                .build();

        Category teas = Category.builder()
                .name("Herbal teas")
                .description("Herbal teas with different additives")
                .build();

        Category cookies = Category.builder()
                .name("Cookies")
                .description("Excellent addition to drinks")
                .build();
        /* categories */

        categoryRepository.saveAll(asList(
                coffee, teas, cookies
        ));

        /* SUPPLIERS */
        Supplier supplier1 = Supplier.builder()
                .name("DeliveryProductsService")
                .phone("1234567890")
                .email("del@gmail.com")
                .otherDetails("Products from Europe")
                .build();

        Supplier supplier2 = Supplier.builder()
                .name("DeliveryProductsService2")
                .phone("0987654321")
                .email("del2@gmail.com")
                .otherDetails("Products from America")
                .build();
        /* suppliers */

        supplierRepository.saveAll(asList(
                supplier1, supplier2
        ));


        /* PRODUCTS */
        Product americano = Product.builder()
                .category(coffee)
                .name("Caffe Americano")
                .description("A coffee drink prepared by diluting an espresso with hot water.")
                .price(2)
                .supplier(supplier1)
                .status(ProductStatus.DELETED)
                .build();

        Product latte = Product.builder()
                .category(coffee)
                .name("Caffe Latte")
                .description("A coffee drink made with espresso and steamed milk.")
                .price(3)
                .supplier(supplier1)
                .status(ProductStatus.PUBLISHED)
                .build();

        Product masala = Product.builder()
                .category(teas)
                .name("Masala chai")
                .description("a flavoured tea beverage made by brewing black tea with a mixture of aromatic Indian spices and herbs.")
                .price(2)
                .supplier(supplier1)
                .status(ProductStatus.DRAFT)
                .build();

        Product vanilla = Product.builder()
                .category(teas)
                .name("Vanilla tea")
                .description(" a warm beverage that contains black tea, vanilla flavoring and steamed milk.")
                .price(2)
                .supplier(supplier1)
                .status(ProductStatus.DRAFT)
                .build();
        /* products */

        productRepository.saveAll(asList(
                americano, latte, masala, vanilla
        ));


        /* LOGINS */
        Login user1 = Login.builder()
                .username("user1")
                .password("pass1")
                .build();

        Login user2 = Login.builder()
                .username("user2")
                .password("pass2")
                .build();
        /* logins */

        loginRepository.saveAll(asList(
                user1, user2
        ));


        /* DISCOUNTS */
        Discount discount1 = Discount.builder()
                .ordersNumber(12)
                .discountPercent(10)
                .status(DiscountStatus.VALID)
                .build();

        Discount discount2 = Discount.builder()
                .ordersNumber(20)
                .discountPercent(10)
                .status(DiscountStatus.EXPIRED)
                .build();
        /* discounts */

        discountRepository.saveAll(asList(
                discount1, discount2
        ));


        /* CUSTOMERS */
        Customer customer1 = Customer.builder()
                .firstName("Michael")
                .lastName("Adams")
                .email("ma@icloud.com")
                .phone("1234321")
                .login(user1)
                .discount(discount1)
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Tommy")
                .lastName("Charlies")
                .email("tch@icloud.com")
                .phone("2345432")
                .login(user2)
                .discount(discount2)
                .build();
        /* customers */

        customerRepository.saveAll(asList(
                customer1, customer2
        ));

        /* ORDERS */
        OrderItem order1 = OrderItem.builder()
                .date("22/03/2020")
                .status("CANCELLED")
                .total(0)
                .customer(customer1)
                .products(asList(masala, vanilla))
                .build();

        OrderItem order2 = OrderItem.builder()
                .date("20/03/2020")
                .status("CANCELLED")
                .total(0)
                .customer(customer2)
                .products(asList(americano, vanilla))
                .build();

        OrderItem order3 = OrderItem.builder()
                .date("21/03/2020")
                .status("FINISHED")
                .total(10)
                .customer(customer1)
                .products(asList(vanilla, latte))
                .build();
        /* orders */

        orderRepository.saveAll(asList(
                order1, order2, order3
        ));

    }

}
