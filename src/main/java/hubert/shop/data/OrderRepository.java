package hubert.shop.data;

import hubert.shop.model.Order;
import hubert.shop.model.Product;
import hubert.shop.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUser(User user);
}
