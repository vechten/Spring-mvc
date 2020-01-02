package hubert.shop.data;

import hubert.shop.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, String> {
}
