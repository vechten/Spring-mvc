package hubert.shop.data;

import hubert.shop.model.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
