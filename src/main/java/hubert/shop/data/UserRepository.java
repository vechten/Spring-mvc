package hubert.shop.data;

import hubert.shop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query
    User findByUsername(String username);
}
