package hubert.shop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Cart {
    @Id
    private UUID orderId;
    @ManyToMany(targetEntity = Product.class)
    @Size(min=1, message = "You must choose at least 1 product")
    private List<UUID> products;

}
