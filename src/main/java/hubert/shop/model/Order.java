package hubert.shop.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID orderId;


    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @ManyToMany(targetEntity = Cart.class)
    private List<Cart> carts = new ArrayList<>();

    public void addDesign(Cart design) {
        this.carts.add(design);
    }

}
