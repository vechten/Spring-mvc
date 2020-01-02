package hubert.shop;

import hubert.shop.data.ProductRepository;
import hubert.shop.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(ProductRepository repo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Product(UUID.randomUUID(), "Pizza de la mount", Product.Type.PIZZA, "Italian, food, pizza"));
                repo.save(new Product(UUID.randomUUID(), "Spaghetti Carbonare", Product.Type.PASTA, "Italian, pasta, food"));
                repo.save(new Product(UUID.randomUUID(), "Coke", Product.Type.DRINK, "coke, drink, cola"));
                repo.save(new Product(UUID.randomUUID(), "Nachos", Product.Type.NACHOS, "coke, drink, cola"));
                repo.save(new Product(UUID.randomUUID(), "Sauce BBQ", Product.Type.SAUCE, "bbq"));
            }
        };
    }
}
