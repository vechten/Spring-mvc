package hubert.shop.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Product {

    @Id
    private final String name;
    private final Type type;
    private final String keywords;
    public static enum Type {
        PIZZA, DRINK, PASTA, NACHOS, SAUCE
    }
}
