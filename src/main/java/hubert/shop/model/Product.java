package hubert.shop.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Product {

    @Id
    @NotBlank
    private String name;
    private Type type;
    @NotBlank
    private String keywords;
    private boolean active;

    public Product() {
        this.active = true;
    }

    public Product(@NotBlank String name, @NotBlank Type type, @NotBlank String keywords) {
        this.name = name;
        this.type = type;
        this.keywords = keywords;
        this.active = true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public enum Type {
        PIZZA, DRINK, PASTA, NACHOS, SAUCE
    }
}
