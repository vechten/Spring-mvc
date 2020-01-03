package hubert.shop.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinTable(joinColumns = @JoinColumn(name = "cart"),
              inverseJoinColumns = @JoinColumn(name = "product"))
    @ManyToMany(targetEntity = Product.class)
    private List<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return "Your id cart is " +
                "" + id +
                " and you have inside " + products.size() +
                " products";
    }
}
