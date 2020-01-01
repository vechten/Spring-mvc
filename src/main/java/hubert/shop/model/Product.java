package hubert.shop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Product {
    private final UUID productId;
    private final String name;
    private final Type type;
    private final String keywords;


    public static enum Type {
        PIZZA, DRINK, PASTA, NACHOS, SAUCE
    }
}
